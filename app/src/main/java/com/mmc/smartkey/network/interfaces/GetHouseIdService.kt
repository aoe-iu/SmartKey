package com.mmc.smartkey.network.interfaces

import com.mmc.smartkey.network.model.HouseIdResult
import com.mmc.smartkey.network.params.HouseIDParams
import retrofit2.Call
import retrofit2.http.*

interface GetHouseIdService {
    @POST("iacs/info/host/people/findFreezeAndActiveAppNew")
    fun getHouseId(@Query("token") token: String, @Body params: HouseIDParams): Call<HouseIdResult>
}