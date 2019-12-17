package com.movilbox.mbprobe.di.login

import android.content.SharedPreferences
import com.movilbox.mbprobe.model.repositories.LoginRespositoryImpl
import com.movilbox.mbprobe.model.retrofit.webServices.LoginWS
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class LoginModule{

    @LoginScope
    @Provides
    fun providesLoginRepository(loginWS: LoginWS, sharedPreferences: SharedPreferences): LoginRespositoryImpl{
        return LoginRespositoryImpl(loginWS, sharedPreferences)
    }

    @LoginScope
    @Provides
    fun providesLoginWS(retrofit: Retrofit): LoginWS {
        return retrofit.create(LoginWS::class.java)
    }

}