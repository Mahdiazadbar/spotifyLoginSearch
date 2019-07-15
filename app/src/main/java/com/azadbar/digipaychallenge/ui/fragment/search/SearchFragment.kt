package com.azadbar.digipaychallenge.ui.fragment.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.azadbar.digipaychallenge.R
import com.azadbar.digipaychallenge.di.DependencyInjectorImpl
import com.azadbar.digipaychallenge.model.ArtistItems
import com.azadbar.digipaychallenge.ui.adapter.RecyclerAdapter
import com.azadbar.digipaychallenge.utility.Storage
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragmnet_search.*
import retrofit2.Retrofit
import javax.inject.Inject

class SearchFragment : DaggerFragment(), TextWatcher, SearchContract.View {


    private lateinit var presenter: SearchContract.Presenter
    @Inject
    lateinit var retrofit: Retrofit


    lateinit var adapter: RecyclerAdapter
    lateinit var prefs: Storage

    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragmnet_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        button_search.setOnClickListener {
            search(search_edit_text.text.toString())
        }

        search_edit_text.addTextChangedListener(this)

        adapter = RecyclerAdapter()
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(context)


        prefs = Storage(context)
        setPresenter(SearchPresenter(this, DependencyInjectorImpl()))

    }


    override fun setPresenter(presenter: SearchContract.Presenter) {
        this.presenter = presenter
    }

    override fun onLoadSearch(items: List<ArtistItems>) {
        activity!!.runOnUiThread {
            adapter.items= items.toMutableList()
            adapter.notifyDataSetChanged()
        }

    }

    override fun onLoadFail() {
        Toast.makeText(context, getText(R.string.load_data_login), Toast.LENGTH_SHORT).show()
    }


    @SuppressLint("CheckResult")
    fun search(searchText: String) {
        if (!searchText.isBlank()) {
            presenter.startSearch(searchText, retrofit)
        } else {
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