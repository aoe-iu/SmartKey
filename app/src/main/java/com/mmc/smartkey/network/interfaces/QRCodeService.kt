package com.mmc.smartkey.network.interfaces
import com.mmc.smartkey.App
import com.mmc.smartkey.network.model.QrCodeResult
import com.mmc.smartkey.network.params.QRCodeParams
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface QRCodeService {
    @POST("iacs/host/data/qrcode/app")
    fun makeQRCode(@Query("token") token: String, @Body qrCodeParams: QRCodeParams): Call<QrCodeResult>
}