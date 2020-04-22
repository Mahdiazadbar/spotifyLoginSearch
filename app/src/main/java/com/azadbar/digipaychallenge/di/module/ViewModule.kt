package com.azadbar.digipaychallenge.di.module

import com.azadbar.digipaychallenge.ui.fragment.login.LoginContract
import com.azadbar.digipaychallenge.ui.fragment.login.LoginFragment
import com.azadbar.digipaychallenge.ui.fragment.search.SearchContract
import com.azadbar.digipaychallenge.ui.fragment.search.SearchFragment
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModule{
    @Binds
    abstract fun provideLoginView(loginFragment: LoginFragment):LoginContract.View

    @Binds
    abstract fun provideSearchView(searchFragment: SearchFragment):SearchContract.View


}