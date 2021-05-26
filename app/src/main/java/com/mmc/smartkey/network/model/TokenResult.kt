package com.mmc.smartkey.network.model

class TokenResult(
    val status: Int,
    val result: Int,
    val data: Data,
    val code: String,
    val message: String
) {

    class Data(
        val unionId: String,
        val peopleId: String,
        val houseID: String,
        val authority: Int,
        val name: String,
        val id: String,
        val account: String,
        val token: String,
        val status: Int
    )
}