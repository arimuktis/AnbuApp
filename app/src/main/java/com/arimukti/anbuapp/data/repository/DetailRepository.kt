package com.arimukti.anbuapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.arimukti.anbuapp.data.remote.AnbuApi
import com.arimukti.anbuapp.data.remote.ReviewPagingSource
import com.arimukti.anbuapp.data.remote.TrailerPagingSource
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

    suspend fun getTrailers(id: String) = anbuApi.getTrailers(id)



}