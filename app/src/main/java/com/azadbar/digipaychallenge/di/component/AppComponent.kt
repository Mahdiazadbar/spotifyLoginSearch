package com.azadbar.digipaychallenge.di.component

import android.app.Application
import com.azadbar.digipaychallenge.App
import com.azadbar.digipaychallenge.di.module.AppModule
import com.azadbar.digipaychallenge.di.module.ActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, AndroidInjectionModule::class, ActivityModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }


    fun inject(app:App)







}