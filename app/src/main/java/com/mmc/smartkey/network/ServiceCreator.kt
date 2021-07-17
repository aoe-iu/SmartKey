package com.mmc.smartkey.network

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {

    private const val BASE_URL = "https://pabaspmj.szxhdz.com:18000/xhapp/service/"

    private val LoggingInterceptor =
        HttpLoggingInterceptor { Log.d("net", it) }.setLevel(
            HttpLoggingInterceptor.Level.BODY
        )

    private val okHttpClientBuilder = OkHttpClient.Builder()
/*        .addInterceptor {
            it.proceed(
                it.request().newBuilder()
                    .addHeader("TOKEN", "asdadsdsa")
                    .build()
            )
        }*/
        .addInterceptor(LoggingInterceptor)

    private val retrofit = Retrofit.Builder()
//        .client(okHttpClientBuilder.build())
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)

}
