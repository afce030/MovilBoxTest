package com.movilbox.mbprobe.view.fragments.login_navigation

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.movilbox.mbprobe.R
import com.movilbox.mbprobe.utils.Functions.validateEmail
import com.movilbox.mbprobe.view.activities.MainActivity
import com.movilbox.mbprobe.viewmodels.LoginViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_email_login.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class EmailLoginFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var vm: LoginViewModel

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_email_login, container, false)
        vm = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]

        vm.loginResponse.observe(viewLifecycleOwner, Observer { data ->
            when(data.success){
                true -> {
                    context?.startActivity(Intent(context, MainActivity::class.java))
                    activity?.finish()
                }
                false -> {
                }
            }
        })

        autoLogin(view)
        setupUi(view)

        return view
    }

    private fun autoLogin(view: View){

        val conectivity = view.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = conectivity.activeNetworkInfo

        if(networkInfo != null && networkInfo.isConnected) {

            val email = sharedPreferences.getString("email", "vacio")
            val password = sharedPreferences.getString("password", "vacio")

            if(email != "vacio" && password != "vacio") {
                vm.requestLogin(email!!, password!!)
            }

        }else{

            val authState = sharedPreferences.getBoolean("LoginOfflineStatus", false)

            if(authState) {
                context?.startActivity(Intent(context, MainActivity::class.java))
                activity?.finish()
            }

        }

    }

    private fun setupUi(view: View){

        val initSession = view.findViewById<Button>(R.id.btn_initSession)

        initSession?.setOnClickListener {

            val email = et_email.text.toString()

            val conectivity = view.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = conectivity.activeNetworkInfo

            if(networkInfo != null && networkInfo.isConnected) {

                when (validateEmail(email)) {
                    true -> {
                        vm.requestLogin(email, et_password.text.toString())
                    }
                    false -> {

                        AlertDialog.Builder(context!!)
                            .setTitle("Email incorrecto")
                            .setMessage("El formato ingresado es incorrecto")
                            .setNegativeButton(android.R.string.no, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show()

                    }
                }

            }else{

                AlertDialog.Builder(context!!)
                    .setTitle("No tienes conexión")
                    .setMessage("Verifica tu conexión a internet")
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()

            }


        }

    }

}
