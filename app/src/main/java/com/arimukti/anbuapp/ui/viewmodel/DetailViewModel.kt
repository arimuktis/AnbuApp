package com.arimukti.anbuapp.ui.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.arimukti.anbuapp.data.repository.DetailRepository
import com.arimukti.anbuapp.data.model.TrailerResponse
import kotlinx.coroutines.launch

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

    private val _response = MutableLiveData<TrailerResponse>()
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

    val trailers : LiveData<TrailerResponse>
        get() = _response

   fun getTrailers(query: String) = viewModelScope.launch {
        repository.getTrailers(query).let { response ->
           _response.postValue(response)
        }
    }

    fun getReview(query: String) {
        currentQuery.value = query
    }

    fun getTrailer(query: String) {
        currentTrailer.value = query
    }

}