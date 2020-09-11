package com.zackyasgar.at_tauba

import com.zackyasgar.at_tauba.Constans.Companion.CONTENT_TYPE
import com.zackyasgar.at_tauba.Constans.Companion.SERVER_KEY
import com.zackyasgar.at_tauba.model.PushNotifikasi
import retrofit2.Response
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NotifikasiAPI {

    @Headers("Authorization: key=$SERVER_KEY", "Content-Type: $CONTENT_TYPE")
    @POST ("fcm/send")
    suspend fun postNotivication(
        @Body notification: PushNotifikasi
    ): Response<ResponseBody>
}