package com.movilbox.mbprobe.model.repositories

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.movilbox.mbprobe.model.retrofit.dto.loginResponse.LoginResponse
import com.movilbox.mbprobe.model.retrofit.webServices.LoginWS
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LoginRespositoryImpl @Inject constructor(
    private val loginWS: LoginWS,
    private val sharedPreferences: SharedPreferences
): LoginRepository {

    override fun requestAuthInfo(
        email: String,
        password: String,
        loginReponse: MutableLiveData<LoginResponse>
    ) {

        val call = loginWS.getAuthToken(email, password)

        call.enqueue(object : Callback<LoginResponse>{

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("loginResponse", t.message)
                loginReponse.postValue(LoginResponse())
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                when {
                    response.code() == 200 -> {
                        val infoBody = response.body()

                        loginReponse.postValue(infoBody)
                        val editor = sharedPreferences.edit()
                        editor.putString("email", email)
                        editor.putString("password", password)
                        editor.putString("authToken", infoBody?.authToken ?: "")
                        editor.putBoolean("LoginOfflineStatus", true)
                        editor.apply()

                    }
                    else -> {
                        Log.d("loginResponse", response.raw().toString())
                        loginReponse.postValue(LoginResponse())
                    }
                }

            }

        })

    }

}