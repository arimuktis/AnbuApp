package com.arimukti.anbuapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.arimukti.anbuapp.data.remote.AnbuApi
import com.arimukti.anbuapp.data.remote.GenrePagingSource
import javax.inject.Inject

class GenreRepository @Inject constructor(private val anbuApi: AnbuApi){
    fun getGenre() =
        Pager(
            config =  PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { GenrePagingSource(anbuApi,null,null) }
        ).liveData
}