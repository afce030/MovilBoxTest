package com.movilbox.mbprobe.di.login

import androidx.lifecycle.ViewModel
import com.movilbox.mbprobe.di.factory.viewModelFactory.ViewModelKey
import com.movilbox.mbprobe.viewmodels.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LoginViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindsLoginViewModel(loginViewModel: LoginViewModel): ViewModel

}