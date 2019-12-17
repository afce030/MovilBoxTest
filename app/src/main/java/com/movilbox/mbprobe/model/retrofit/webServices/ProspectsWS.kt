package com.movilbox.mbprobe.model.retrofit.webServices

import com.movilbox.mbprobe.model.retrofit.dto.prospectsResponse.ProspectsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ProspectsWS {

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("/sch/prospects.json")
    fun getProspectsList(
        @Header("token") token: String,
        @Query("page") page: Int
    ): Call<ArrayList<ProspectsResponse>>

}