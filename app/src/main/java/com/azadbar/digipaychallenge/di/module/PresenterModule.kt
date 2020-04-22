package com.azadbar.digipaychallenge.di.module

import com.azadbar.digipaychallenge.data.SpotifyRepositoryImpl
import com.azadbar.digipaychallenge.ui.fragment.login.LoginContract
import com.azadbar.digipaychallenge.ui.fragment.login.LoginPresenter
import com.azadbar.digipaychallenge.ui.fragment.search.SearchContract
import com.azadbar.digipaychallenge.ui.fragment.search.SearchPresenter
import com.azadbar.digipaychallenge.utility.AppSharedPreferences
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {

    @Provides
    fun provideLoginPresenter(view: LoginContract.View, appSharedPreferences: AppSharedPreferences): LoginPresenter {
        return LoginPresenter(view,appSharedPreferences)
    }

    @Provides
    fun provideSearchPresenter(view: SearchContract.View, spotifyRepositoryImpl: SpotifyRepositoryImpl): SearchPresenter {
        return SearchPresenter(view,spotifyRepositoryImpl)
    }


}