package com.mmc.smartkey.ui

import androidx.lifecycle.*
import com.mmc.smartkey.network.Repository
import com.mmc.smartkey.network.params.KeyParams
import com.mmc.smartkey.network.params.QRCodeParams

class MainActivityViewModel : ViewModel() {

//    private val qrCodeLiveData = MutableLiveData<QRCodeParams>()
//
//    val qrLiveData = Transformations.switchMap(qrCodeLiveData) { qrCodeParams ->
//        Repository.makeQRCode(qrCodeParams)
//    }
//
//    fun searchQRData(qrCodeParams: QRCodeParams) {
//        qrCodeLiveData.value = qrCodeParams
//    }

    private val _openDoorLiveData = MutableLiveData<KeyParams>()

    val openDoorLiveData = Transformations.switchMap(_openDoorLiveData) { keyParams ->
        Repository.openDoor(keyParams)
    }

    fun openDoor(keyParams: KeyParams) {
        _openDoorLiveData.value = keyParams
    }

    private val _tokenLiveData = MutableLiveData<String>()

    val tokenLiveData = Transformations.switchMap(_tokenLiveData) { unionId ->
//        Repository.getToken(unionId)
        Repository.refreshToken(unionId)
    }

    fun getToken(unionId: String) {
        _tokenLiveData.value = unionId
    }
}