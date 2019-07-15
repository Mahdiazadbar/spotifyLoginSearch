package com.azadbar.digipaychallenge.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.azadbar.digipaychallenge.utility.PrefStore
import com.azadbar.digipaychallenge.R
import com.azadbar.digipaychallenge.model.ArtistItems
import com.azadbar.digipaychallenge.rest.SpotifyApi
import com.azadbar.digipaychallenge.ui.adapter.RecyclerAdapter
import dagger.android.support.DaggerFragment
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragmnet_search.*
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchFragment : DaggerFragment(), TextWatcher {


    lateinit var spotifyApi: SpotifyApi

    lateinit var adapter: RecyclerAdapter

    lateinit var prefs: PrefStore

    @Inject
    lateinit var retrofit: Retrofit

    companion object {

        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragmnet_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        createUtils()

        button_search.setOnClickListener {
            search(search_edit_text.text.toString())
        }


        search_edit_text.addTextChangedListener(this)

        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(context)

    }

    fun createUtils() {
        spotifyApi = retrofit.create(SpotifyApi::class.java)
        prefs = PrefStore(context)
        adapter = RecyclerAdapter()
    }


    @SuppressLint("CheckResult")
    fun search(searchText:String) {
        if(!searchText.isBlank()) {
            spotifyApi
                    .searchSpotify(searchText, "track,artist")
                    .subscribeOn(Schedulers.io())
                    .debounce(1,TimeUnit.SECONDS)
                    .subscribe { result ->
                        activity!!.runOnUiThread {
                            if (result.artists.items.isNotEmpty()) {
                                adapter.items = result.artists.items as MutableList<ArtistItems>
                                adapter.notifyDataSetChanged()
                            }
                        }
                    }

        }else{
            adapter.items.clear()
            adapter.notifyDataSetChanged()
        }

    }

    override fun afterTextChanged(p0: Editable?) {

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        search(p0.toString())
    }



}