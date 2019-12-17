package com.movilbox.mbprobe.model.repositories

import androidx.lifecycle.MutableLiveData
import com.movilbox.mbprobe.model.retrofit.dto.loginResponse.LoginResponse

interface LoginRepository {

    fun requestAuthInfo(
        email: String,
        password: String,
        loginReponse: MutableLiveData<LoginResponse>
    )

}