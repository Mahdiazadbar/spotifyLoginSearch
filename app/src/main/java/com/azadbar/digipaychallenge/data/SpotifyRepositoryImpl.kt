package com.azadbar.digipaychallenge.data

import com.azadbar.digipaychallenge.rest.SpotifyApi
import dagger.android.AndroidInjection
import dagger.android.DaggerApplication
import dagger.android.DaggerContentProvider
import dagger.android.support.DaggerFragment
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SpotifyRepositoryImpl @Inject constructor(retrofit: Retrofit) : SpotifyRepository {



    private var spotifyApi: SpotifyApi

    init {
        spotifyApi = retrofit.create(SpotifyApi::class.java)
    }

    override fun loadSearch(text: String, listener: SerchRepositoryListener) {

        spotifyApi
            .searchSpotify(text, "track,artist")
            .subscribeOn(Schedulers.io())
            .debounce(1, TimeUnit.SECONDS)
            .subscribe { result ->
                listener.onLoadSearch(result)
            }


    }


}