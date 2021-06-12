package com.example.anbuapp.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
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
            pagingSourceFactory = {MoviePagingSource(anbuApi,null)}
        ).liveData

    fun getSearchMovies(query: String) =
        Pager(
            config =  PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {MoviePagingSource(anbuApi,query)}
        ).liveData
}