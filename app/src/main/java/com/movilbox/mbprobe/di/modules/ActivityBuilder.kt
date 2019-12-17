package com.movilbox.mbprobe.di.modules

import com.movilbox.mbprobe.view.activities.LoginActivity
import com.movilbox.mbprobe.view.activities.MainActivity
import com.movilbox.mbprobe.di.login.LoginFragmentBuilder
import com.movilbox.mbprobe.di.login.LoginModule
import com.movilbox.mbprobe.di.login.LoginViewModelModule
import com.movilbox.mbprobe.di.login.LoginScope
import com.movilbox.mbprobe.di.main.MainFragmentBuilder
import com.movilbox.mbprobe.di.main.MainModule
import com.movilbox.mbprobe.di.main.MainScope
import com.movilbox.mbprobe.di.main.MainViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder{

    @LoginScope
    @ContributesAndroidInjector(modules = [LoginModule::class, LoginFragmentBuilder::class, LoginViewModelModule::class])
    abstract fun bindsLoginActivity(): LoginActivity

    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class, MainFragmentBuilder::class, MainViewModelModule::class])
    abstract fun bindsMainActivity(): MainActivity

}