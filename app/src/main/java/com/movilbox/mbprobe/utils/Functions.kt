package com.movilbox.mbprobe.utils

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.movilbox.mbprobe.view.activities.LoginActivity

object Functions {

    fun closeSession(activity: AppCompatActivity, sharedPreferences: SharedPreferences){

        val editor = sharedPreferences.edit()
        editor.remove("email")
        editor.remove("password")
        editor.remove("authToken")
        editor.remove("LoginOfflineStatus")
        editor.apply()

        activity.startActivity(Intent(activity, LoginActivity::class.java))
        activity.finish()

    }

    fun validateEmail(email: String): Boolean{
        val regex = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-z]{0,64}\\.com".toRegex()
        return regex.containsMatchIn(email)
    }

}