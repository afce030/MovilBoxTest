package com.movilbox.mbprobe.model.retrofit.webServices

import com.movilbox.mbprobe.model.retrofit.dto.loginResponse.LoginResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LoginWS {

    @GET("application/login")
    fun getAuthToken(
        @Query("email") email: String,
        @Query("password") password: String
    ): Call<LoginResponse>

}