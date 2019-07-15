package com.azadbar.digipaychallenge.data

import com.azadbar.digipaychallenge.model.SearchResponse;


interface SpotifyRepository {
    fun loadSearch(text: String,listener: SerchRepositoryListener)
}