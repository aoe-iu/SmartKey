package com.mmc.smartkey.network.interfaces

import com.mmc.smartkey.network.model.OpenDoorResult
import com.mmc.smartkey.network.params.KeyParams
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface OpenDoorService {
    @POST("iacs/info/house/host/commandByHouseHostId?token=\${App.token}")
    fun openDoor(@Body key: KeyParams): Call<OpenDoorResult>
}