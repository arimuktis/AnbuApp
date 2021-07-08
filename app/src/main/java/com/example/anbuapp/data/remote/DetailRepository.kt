package com.example.anbuapp.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailRepository @Inject constructor(private val anbuApi: AnbuApi) {
    fun getReview(id: String) =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ReviewPagingSource(anbuApi, id) }
        ).liveData

    fun getTrailer(id: String) =
        Pager(
            config = PagingConfig(
                pageSize = 2,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { TrailerPagingSource(anbuApi, id) }
        ).liveData

    suspend fun getasda(id:String) = anbuApi.getTrailer(id,1)
}