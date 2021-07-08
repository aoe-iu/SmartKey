package com.mmc.smartkey

import android.app.Application
import com.mmc.smartkey.network.KeyConfig

class App : Application() {

    companion object {
//        const val unionId = "xxxxx" //change to your own id
        var unionId = ""
        var houseId = ""
        var userId = ""
        var token = ""
    }

    override fun onCreate() {
        super.onCreate()
        val token = KeyConfig.getInstance(this).getToken()
        val houseId = KeyConfig.getInstance(this).getHouseID()
        val userId = KeyConfig.getInstance(this).getUserID()
        if (token != null) App.token = token
        if (houseId != null) App.houseId = houseId
        if (userId != null) App.userId = userId
    }
}