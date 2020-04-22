package com.azadbar.digipaychallenge.data


import com.azadbar.digipaychallenge.model.SearchResponse
import com.azadbar.digipaychallenge.data.rest.ApiInterface
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SpotifyRepositoryImpl @Inject  constructor(private var apiInterface: ApiInterface)  : SpotifyRepository {




    override fun loadSearch(text: String): Observable<SearchResponse> {

        return apiInterface
            .searchSpotify(text, "track,artist")
            .subscribeOn(Schedulers.io())
            .debounce(1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())


    }


}