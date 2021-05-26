package com.mmc.smartkey.network

import com.mmc.smartkey.network.interfaces.*
import com.mmc.smartkey.network.params.HouseIDParams
import com.mmc.smartkey.network.params.KeyParams
import com.mmc.smartkey.network.params.QRCodeParams
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object SmartKeyNetwork {

    private val openDoorService = ServiceCreator.create(OpenDoorService::class.java)

    suspend fun openDoor(keyParams: KeyParams) = openDoorService.openDoor(keyParams).await()

    private val qrCodeService = ServiceCreator.create(QRCodeService::class.java)

    suspend fun makeQRCode(qrCodeParams: QRCodeParams) =
        qrCodeService.makeQRCode(qrCodeParams).await()

    private val tokenService = ServiceCreator.create(GetTokenService::class.java)

    suspend fun getToken(unionId: String) = tokenService.getToken(unionId).await()

    private val houseIDService = ServiceCreator.create(GetHouseIdService::class.java)

    suspend fun getHouseID(houseIDParams: HouseIDParams) =
        houseIDService.getHouseId(houseIDParams).await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }

}