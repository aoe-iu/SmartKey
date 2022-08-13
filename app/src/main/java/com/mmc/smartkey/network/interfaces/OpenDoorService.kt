package com.mmc.smartkey.network.interfaces

import com.mmc.smartkey.network.model.OpenDoorResult
import com.mmc.smartkey.network.params.KeyParams
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface OpenDoorService {
    @POST("iacs/info/house/host/commandByHouseHostId")
    fun openDoor(@Query("token") token: String, @Body key: KeyParams): Call<OpenDoorResult>
}