package com.azadbar.digipaychallenge.ui.fragment.search

import com.azadbar.digipaychallenge.data.SerchRepositoryListener
import com.azadbar.digipaychallenge.di.DependencyInjector
import com.azadbar.digipaychallenge.model.SearchResponse
import retrofit2.Retrofit

class SearchPresenter(
    view: SearchContract.View,
    private var dependencyInjector: DependencyInjector
) : SearchContract.Presenter, SerchRepositoryListener {


    private var view: SearchContract.View? = view

    override fun onDestroy() {
        this.view = null
    }

    override fun startSearch(serchText: String,retrofit: Retrofit) {
        val spotifyRepository = dependencyInjector.spotifyRepository(retrofit)
        spotifyRepository.loadSearch(serchText,this)
    }
    override fun onLoadSearch(response: SearchResponse) {
        view?.onLoadSearch(response.artists.items)
    }


}