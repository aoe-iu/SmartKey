package com.mmc.smartkey.network.interfaces
import com.mmc.smartkey.App
import com.mmc.smartkey.network.model.QrCodeResult
import com.mmc.smartkey.network.params.QRCodeParams
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface QRCodeService {
    @POST("iacs/host/data/qrcode/app?token=\${App.token}")
    fun makeQRCode(@Body qrCodeParams: QRCodeParams): Call<QrCodeResult>
}