package com.azadbar.digipaychallenge.ui.fragment.search

import androidx.annotation.NonNull
import com.azadbar.digipaychallenge.data.SpotifyRepositoryImpl
import com.azadbar.digipaychallenge.model.SearchResponse
import com.azadbar.digipaychallenge.utility.AppSharedPreferences
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchPresenter constructor(view: SearchContract.View,
                                          private var spotifyRepository: SpotifyRepositoryImpl): SearchContract.Presenter {


    private val compositeDisposable = CompositeDisposable()

    val searchResultsObservable: (String) -> Observable<SearchResponse> = { query -> spotifyRepository.loadSearch(query) }

    private var view: SearchContract.View? = view

    override fun onDestroy() {
        this.view = null
    }

    override fun startSearch(serchText: String) {


        val searchResultsDisposable = searchResultsObservable(serchText)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(observer)

        compositeDisposable.add(searchResultsDisposable)
    }



    val observer: DisposableObserver<SearchResponse>
        get() = object : DisposableObserver<SearchResponse>() {

            override fun onNext(@NonNull searchResponse: SearchResponse) {
                if (searchResponse.artists.items.isNotEmpty())
                    view?.onLoadSearch(searchResponse.artists.items)
                else
                    view?.onLoadFail()
            }

            override fun onError(@NonNull e: Throwable) {
                e.printStackTrace()
                view?.onLoadFail()
            }

            override fun onComplete() {}
        }


}