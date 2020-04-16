package com.azadbar.digipaychallenge.data.rest

import com.azadbar.digipaychallenge.model.SearchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface SpotifyApiInterface {
    @GET("search")
    fun searchSpotify(@Header("Authorization") token:String,@Query("q") query: String, @Query("type") type: String): Observable<SearchResponse>
}