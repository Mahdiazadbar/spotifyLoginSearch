package com.azadbar.digipaychallenge.ui.fragment.search

import com.azadbar.digipaychallenge.base.BasePresenter
import com.azadbar.digipaychallenge.base.BaseView
import com.azadbar.digipaychallenge.model.ArtistItems
import retrofit2.Retrofit

interface SearchContract {
    interface Presenter : BasePresenter {
        fun startSearch(serchText: String)
    }

    interface View  {
        fun onLoadSearch(items: List<ArtistItems>)
        fun onLoadFail()
    }
}
