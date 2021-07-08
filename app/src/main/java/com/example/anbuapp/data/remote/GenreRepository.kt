package com.example.anbuapp.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import javax.inject.Inject

class GenreRepository @Inject constructor(private val anbuApi: AnbuApi){
    fun getGenre() =
        Pager(
            config =  PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {GenrePagingSource(anbuApi,null,null)}
        ).liveData

}