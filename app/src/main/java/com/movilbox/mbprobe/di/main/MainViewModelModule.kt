package com.movilbox.mbprobe.di.main

import androidx.lifecycle.ViewModel
import com.movilbox.mbprobe.di.factory.viewModelFactory.ViewModelKey
import com.movilbox.mbprobe.viewmodels.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindsMainViewModel(mainViewModel: MainViewModel): ViewModel

}