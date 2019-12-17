package com.movilbox.mbprobe.di.main

import com.movilbox.mbprobe.model.persistence.databases.ProspectsDB
import com.movilbox.mbprobe.model.persistence.methods.ProspectsDAO
import com.movilbox.mbprobe.model.repositories.MainRepositoryImpl
import com.movilbox.mbprobe.model.retrofit.webServices.ProspectsWS
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule{

    @MainScope
    @Provides
    fun providesProspectsDAO(prospectsDB: ProspectsDB): ProspectsDAO{
        return prospectsDB.prospectsDAO()
    }

    @MainScope
    @Provides
    fun providesMainRepository(prospectsWS: ProspectsWS, prospectsDAO: ProspectsDAO): MainRepositoryImpl{
        return MainRepositoryImpl(prospectsWS, prospectsDAO)
    }

    @MainScope
    @Provides
    fun providesProspectsWS(retrofit: Retrofit): ProspectsWS {
        return retrofit.create(ProspectsWS::class.java)
    }

}