package com.movilbox.mbprobe.model.retrofit.dto.loginResponse

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("zone")
	val zone: String? = "",

	@field:SerializedName("success")
	val success: Boolean? = false,

	@field:SerializedName("authToken")
	val authToken: String? = "",

	@field:SerializedName("email")
	val email: String? = ""
)