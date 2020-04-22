package com.azadbar.digipaychallenge.di.module

import com.azadbar.digipaychallenge.ui.fragment.login.LoginFragment
import com.azadbar.digipaychallenge.ui.fragment.search.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector(modules = [PresenterModule::class,ViewModule::class])
    abstract fun contributeLoginFragment(): LoginFragment?

    @ContributesAndroidInjector(modules = [PresenterModule::class,ViewModule::class])
    abstract fun contributeSearchFragment(): SearchFragment?


}