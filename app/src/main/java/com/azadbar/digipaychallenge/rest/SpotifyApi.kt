package com.azadbar.digipaychallenge.rest

import com.azadbar.digipaychallenge.model.SearchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface SpotifyApi {
    @GET("search")
    fun searchSpotify(@Query("q") query: String, @Query("type") type: String): Observable<SearchResponse>
}