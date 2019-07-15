package com.azadbar.digipaychallenge.data

import com.azadbar.digipaychallenge.model.SearchResponse

interface SerchRepositoryListener {
    fun onLoadSearch(response: SearchResponse)
}