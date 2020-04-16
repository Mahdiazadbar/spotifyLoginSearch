package com.azadbar.digipaychallenge.di

import com.azadbar.digipaychallenge.data.SpotifyRepository
import com.azadbar.digipaychallenge.data.SpotifyRepositoryImpl
import retrofit2.Retrofit

class DependencyInjectorImpl : DependencyInjector {
    override fun spotifyRepository(retrofit: Retrofit): SpotifyRepository {
        return SpotifyRepositoryImpl()
    }


}