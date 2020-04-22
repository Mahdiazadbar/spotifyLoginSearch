package com.azadbar.digipaychallenge.di.module

import android.app.Application
import com.azadbar.digipaychallenge.BuildConfig
import com.azadbar.digipaychallenge.data.rest.ApiInterface
import com.azadbar.digipaychallenge.utility.AppSharedPreferences
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideAppSharedPreference(application: Application):AppSharedPreferences{
        return AppSharedPreferences(application)
    }


    @Provides
    fun provideApiClient(sharedPreferences: AppSharedPreferences): ApiInterface {
        val builder = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC
        builder.interceptors().add(logging)
        builder.addInterceptor(
            LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .addHeader("Authorization", sharedPreferences.getAuthToken())
                .addHeader("Accept", "application/json")
                .setLevel(Level.BASIC)
                .request("Request")
                .response("Response")
                .build()
        )
        builder.connectTimeout(60, TimeUnit.SECONDS)
        builder.readTimeout(60, TimeUnit.SECONDS)
        builder.writeTimeout(60, TimeUnit.SECONDS)
        return Retrofit.Builder()
            .baseUrl( "https://api.spotify.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

            .client(builder.build())
            .build()
            .create(ApiInterface::class.java)
    }

}