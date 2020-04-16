package com.azadbar.digipaychallenge.data

import com.azadbar.digipaychallenge.model.ArtistItems
import com.azadbar.digipaychallenge.model.SearchResponse
import io.reactivex.Observable


interface SpotifyRepository {
    fun loadSearch(text: String,token:String): Observable<SearchResponse>
}