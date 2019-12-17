package com.movilbox.mbprobe.di.components

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import android.app.Application
import com.movilbox.mbprobe.BaseApp
import com.movilbox.mbprobe.di.modules.AppModule
import com.movilbox.mbprobe.di.modules.ActivityBuilder
import com.movilbox.mbprobe.di.modules.ViewModelModule
import dagger.BindsInstance
import javax.inject.Singleton

@Singleton
@Component(modules=[
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityBuilder::class,
    ViewModelModule::class
])
interface AppComponent : AndroidInjector<BaseApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun Build(): AppComponent

    }

}
