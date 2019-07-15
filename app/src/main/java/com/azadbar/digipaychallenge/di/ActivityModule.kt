package com.azadbar.digipaychallenge.di

import com.azadbar.digipaychallenge.ui.fragment.login.LoginFragment
import com.azadbar.digipaychallenge.ui.MainActivity
import com.azadbar.digipaychallenge.ui.fragment.search.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    @ActivityScope
    internal abstract fun bindActivity(): MainActivity

    @ContributesAndroidInjector(modules = [LoginFragmentModule::class])
    @ActivityScope
    internal abstract fun bindLoginFragment(): LoginFragment

    @ContributesAndroidInjector(modules = [LoginFragmentModule::class])
    @ActivityScope
    internal abstract fun bindSearchFragment(): SearchFragment




}