package com.arimukti.anbuapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.arimukti.anbuapp.data.remote.AnbuApi
import com.arimukti.anbuapp.data.remote.MoviePagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val anbuApi: AnbuApi){
    fun getNowPlayingMovies() =
        Pager(
            config =  PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(anbuApi,null,null) }
        ).liveData

    fun getMovieByGenre(genre : String) =
        Pager(
            config =  PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(anbuApi,null,genre) }
        ).liveData

    fun getSearchMovies(query: String) =
        Pager(
            config =  PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(anbuApi,query,null) }
        ).liveData
}