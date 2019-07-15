package com.azadbar.digipaychallenge.di

import com.azadbar.digipaychallenge.data.SpotifyRepository
import com.azadbar.digipaychallenge.data.SpotifyRepositoryImpl

class DependencyInjectorImpl : DependencyInjector {
    override fun spotifyRepository(): SpotifyRepository {
        return SpotifyRepositoryImpl()
    }


}