package com.mmc.smartkey.network.interfaces

import com.mmc.smartkey.network.model.TokenResult
import retrofit2.Call
import retrofit2.http.*

interface GetTokenService {
    @GET("system/user/getToken/{unionId}")
    fun getToken(@Path("unionId") unionId: String): Call<TokenResult>
}