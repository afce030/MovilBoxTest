package com.movilbox.mbprobe.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.movilbox.mbprobe.model.persistence.databases.ProspectsDB
import com.movilbox.mbprobe.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule{

    @Provides
    fun provideContext(app: Application): Context{
        return app.baseContext
    }

    @Singleton
    @Provides
    fun providesDatabase(context: Context): ProspectsDB{
        return Room.databaseBuilder(context,
            ProspectsDB::class.java, "prospectsDB").build()
    }

    @Singleton
    @Provides
    fun providesGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Singleton
    @Provides
    fun provideGsonConverter(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val okhttpClientBuilder = OkHttpClient.Builder()
        okhttpClientBuilder.connectTimeout(3, TimeUnit.SECONDS)
        okhttpClientBuilder.readTimeout(3, TimeUnit.SECONDS)
        return okhttpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun providesRetrofitService(okHttpClient: OkHttpClient, gson: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_PATH)
            .client(okHttpClient)
            .addConverterFactory(gson)
            .build()
    }

    @Singleton
    @Provides
    @Named("alias")
    fun providesMasterKey(): String{
        return MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context,
                                 @Named("alias") masterKeyAlias: String): SharedPreferences{

        return EncryptedSharedPreferences.create(
            "ALPHA_DRIVER_SP",
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

}