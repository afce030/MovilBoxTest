package com.movilbox.mbprobe.di.modules

import androidx.lifecycle.ViewModelProvider
import com.movilbox.mbprobe.di.factory.viewModelFactory.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}