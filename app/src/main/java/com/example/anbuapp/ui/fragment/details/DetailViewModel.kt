package com.example.anbuapp.ui.fragment.details

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.anbuapp.data.remote.DetailRepository

class DetailViewModel @ViewModelInject constructor(
    private val repository: DetailRepository,
    @Assisted state: SavedStateHandle
) :
    ViewModel() {

    companion object {
        private const val CURRENT_QUERY = "current_query"
        private const val CURRENT_TRAILER = "current_query"
        private const val EMPTY_QUERY = ""
    }

    private val currentQuery = state.getLiveData(CURRENT_QUERY, EMPTY_QUERY)
    private val currentTrailer = state.getLiveData(CURRENT_TRAILER, EMPTY_QUERY)
    val reviews = currentQuery.switchMap { query ->
        if (query.isNotEmpty()) {
            repository.getReview(query)
        } else {
            repository.getReview(query).cachedIn(viewModelScope)
        }
    }
    val trailer = currentTrailer.switchMap { query ->
        if (query.isNotEmpty()) {
            repository.getTrailer(query)
        } else {
            repository.getTrailer(query).cachedIn(viewModelScope)
        }
    }

    fun getReview(query: String) {
        currentQuery.value = query
    }

    fun getTrailer(query: String) {
        currentTrailer.value = query
    }

}