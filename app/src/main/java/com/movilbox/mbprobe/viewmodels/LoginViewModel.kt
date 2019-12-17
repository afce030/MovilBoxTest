package com.movilbox.mbprobe.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.movilbox.mbprobe.model.repositories.LoginRespositoryImpl
import com.movilbox.mbprobe.model.retrofit.dto.loginResponse.LoginResponse
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginRespositoryImpl: LoginRespositoryImpl): ViewModel() {

    val loginResponse: MutableLiveData<LoginResponse> = MutableLiveData()

    fun requestLogin(email: String, password: String){
        Log.d("loginProcess", "$email $password")
        loginRespositoryImpl.requestAuthInfo(email, password, loginResponse)
    }

}