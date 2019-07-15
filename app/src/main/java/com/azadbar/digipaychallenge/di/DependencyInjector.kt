package com.azadbar.digipaychallenge.di

import com.azadbar.digipaychallenge.data.SpotifyRepository

interface DependencyInjector {
    fun spotifyRepository() : SpotifyRepository
}
