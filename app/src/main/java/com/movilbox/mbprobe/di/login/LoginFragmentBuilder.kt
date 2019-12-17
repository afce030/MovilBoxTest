package com.movilbox.mbprobe.di.login

import com.movilbox.mbprobe.view.fragments.login_navigation.EmailLoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LoginFragmentBuilder{

    @ContributesAndroidInjector
    abstract fun bindsEmailLoginFragment(): EmailLoginFragment

}