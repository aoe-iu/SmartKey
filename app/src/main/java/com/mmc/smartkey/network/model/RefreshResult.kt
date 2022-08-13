package com.mmc.smartkey.network.model

class RefreshResult(
    val houseHostId: String,
    val peopleId: String,
    val token: String,
    val houseAddress: String
)

class RefreshListResult(val peopleId: String, val token: String, val dataResult: List<DataResult>)
