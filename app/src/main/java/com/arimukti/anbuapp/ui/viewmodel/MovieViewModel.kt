package com.arimukti.anbuapp.ui.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.arimukti.anbuapp.data.repository.MovieRepository

class MovieViewModel @ViewModelInject constructor(
    private val repository: MovieRepository,
    @Assisted state: SavedStateHandle
) :
    ViewModel() {

    companion object {
        private const val CURRENT_QUERY = "current_query"
        private const val CURRENT_GENRES = "current_query"
        private const val EMPTY_QUERY = ""
    }

    private val currentQuery = state.getLiveData(CURRENT_QUERY, EMPTY_QUERY)
    val movies = currentQuery.switchMap { query ->
        if (query.contains("|")) {
            repository.getMovieByGenre(query).cachedIn(viewModelScope)
        } else {
            if (query.isNotEmpty()) {
                repository.getSearchMovies(query).cachedIn(viewModelScope)
            } else {
                repository.getNowPlayingMovies().cachedIn(viewModelScope)
            }
        }
    }

    fun searchMovies(query: String) {
        currentQuery.value = query
    }

    fun searchMoviesByGenre(query: String) {
        if (query.isEmpty()) {
            currentQuery.value = query
        } else {
            currentQuery.value = "$query|0"
        }
    }

}