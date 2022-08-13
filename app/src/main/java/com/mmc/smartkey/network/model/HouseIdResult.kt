package com.mmc.smartkey.network.model

import com.google.gson.annotations.SerializedName

data class HouseIdResult(
    val code: String,
    @SerializedName("data") val dataResult: List<DataResult>,
    val message: String,
    val result: Int,
    val status: Int
)

data class DataResult(
    val hostSn: String,
    val houseHostId: String,
    val hostAddress: String,
    val houseAddress: String
)