package com.azadbar.digipaychallenge.data


import com.azadbar.digipaychallenge.model.SearchResponse
import com.azadbar.digipaychallenge.data.rest.RetrofitClient
import com.azadbar.digipaychallenge.data.rest.SpotifyApiInterface
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SpotifyRepositoryImpl  : SpotifyRepository {



    private var spotifyApi: SpotifyApiInterface = RetrofitClient.spotifyApi

    override fun loadSearch(text: String,token:String): Observable<SearchResponse> {

        return spotifyApi
            .searchSpotify(text, "track,artist",token)
            .subscribeOn(Schedulers.io())
            .debounce(1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())


    }


}