package com.arimukti.anbuapp.data.remote

import androidx.paging.PagingSource
import com.arimukti.anbuapp.data.model.ResultX
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class TrailerPagingSource(
    private val anbuApi: AnbuApi,
    private val id: String,
) : PagingSource<Int, ResultX>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultX> {
        return try {
            val position = params.key ?: STARTING_PAGE_INDEX
            val response = anbuApi.getTrailer(id, position)
            val trailer = response.results

            LoadResult.Page(
                data = trailer,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (trailer.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

}

