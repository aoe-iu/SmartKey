package com.mmc.smartkey.network.interfaces

import com.mmc.smartkey.network.model.HouseIdResult
import com.mmc.smartkey.network.params.HouseIDParams
import retrofit2.Call
import retrofit2.http.*

interface GetHouseIdService {
    @POST("iacs/info/host/people/findFreezeAndActiveAppNew?token=\${App.token}")
    fun getHouseId(@Body params: HouseIDParams): Call<HouseIdResult>
}