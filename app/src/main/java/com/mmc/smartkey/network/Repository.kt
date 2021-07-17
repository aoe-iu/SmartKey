package com.mmc.smartkey.network

import androidx.lifecycle.liveData
import com.mmc.smartkey.App
import com.mmc.smartkey.network.model.RefreshResult
import com.mmc.smartkey.network.params.HouseIDParams
import com.mmc.smartkey.network.params.KeyParams
import com.mmc.smartkey.network.params.QRCodeParams
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.CoroutineContext

object Repository {
    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE)
    fun getQRCodeParams(): QRCodeParams {
        val calendar = Calendar.getInstance()
        val startTime = simpleDateFormat.format(calendar.time)
        calendar.add(Calendar.MINUTE, 10)
        val endTime = simpleDateFormat.format(calendar.time)
        return QRCodeParams(App.userId, startTime, endTime, "hostSn")
    }

    fun getKeyParams(): KeyParams {
        return KeyParams(App.houseId, App.userId)
    }

    fun quickOpenDoor(keyParams: KeyParams) {
        CoroutineScope(Dispatchers.IO).launch {
            val openDoorResponse = SmartKeyNetwork.openDoor(keyParams)
            withContext(Dispatchers.Main) {
                if (openDoorResponse.status == 1) {
                    Result.success(openDoorResponse.message)
                } else {
                    Result.failure(RuntimeException("response message is ${openDoorResponse.message}"))
                }
            }
        }

    }

    fun openDoor(keyParams: KeyParams) = fire {
        val openDoorResponse = SmartKeyNetwork.openDoor(keyParams)
        if (openDoorResponse.status == 1) {
            Result.success(openDoorResponse.message)
        } else {
            Result.failure(RuntimeException("response message is ${openDoorResponse.message}"))
        }
    }

    fun makeQRCode(qrCodeParams: QRCodeParams) = fire {
        val qrCodeResult = SmartKeyNetwork.makeQRCode(qrCodeParams)
        if (qrCodeResult.result == 1) {
            Result.success(qrCodeResult.data)
        } else {
            Result.failure(RuntimeException("response message is ${qrCodeResult.message}"))
        }
    }

    fun getToken(unionId: String) = fire {
        val tokenResult = SmartKeyNetwork.getToken(unionId)
        if (tokenResult.status == 1) {
            Result.success(tokenResult.data)
        } else {
            Result.failure(RuntimeException("response message is ${tokenResult.message}"))
        }
    }

    fun refreshToken(unionId: String) = fire {
        val tokenResult = SmartKeyNetwork.getToken(unionId)
        if (tokenResult.status == 1) {
            val houseIdResult =
                SmartKeyNetwork.getHouseID(HouseIDParams(tokenResult.data.peopleId))
            if (houseIdResult.status == 1 && houseIdResult.dataResult.isNotEmpty()) {
                Result.success(
                    RefreshResult(
                        houseIdResult.dataResult[0].houseHostId,
                        tokenResult.data.peopleId,
                        tokenResult.data.token,
                        houseIdResult.dataResult[0].houseAddress
                    )
                )
            } else {
                Result.failure(RuntimeException("response message is ${houseIdResult.message}"))
            }
        } else {
            Result.failure(RuntimeException("response message is ${tokenResult.message}"))
        }
    }


    private fun <T> fire(block: suspend () -> Result<T>) =
        liveData(Dispatchers.IO) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure(e)
            }
            emit(result)
        }

}