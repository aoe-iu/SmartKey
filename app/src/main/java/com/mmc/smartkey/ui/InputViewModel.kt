package com.mmc.smartkey.ui

import androidx.lifecycle.*
import com.mmc.smartkey.network.Repository
import com.mmc.smartkey.network.params.KeyParams
import com.mmc.smartkey.network.params.QRCodeParams

class InputViewModel : ViewModel() {

    private val _tokenLiveData = MutableLiveData<String>()

    val tokenLiveData = Transformations.switchMap(_tokenLiveData) { unionId ->
//        Repository.refreshToken(unionId)
        Repository.refreshList(unionId)
    }

    fun getToken(unionId: String) {
        _tokenLiveData.value = unionId
    }
}