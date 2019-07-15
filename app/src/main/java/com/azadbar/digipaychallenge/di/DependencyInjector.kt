package com.azadbar.digipaychallenge.di

import com.azadbar.digipaychallenge.data.SerchRepositoryListener
import com.azadbar.digipaychallenge.data.SpotifyRepository
import retrofit2.Retrofit

interface DependencyInjector {
    fun spotifyRepository(retrofit: Retrofit) : SpotifyRepository
}
