package com.movilbox.mbprobe.di.main

import com.movilbox.mbprobe.view.fragments.main_navigation.ProspectEditionFragment
import com.movilbox.mbprobe.view.fragments.main_navigation.ProspectsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuilder{

    @ContributesAndroidInjector
    abstract fun providesProspectsListFragment(): ProspectsListFragment

    @ContributesAndroidInjector
    abstract fun providesProspectsEditionFragment(): ProspectEditionFragment

}
