package com.example.anbuapp.data.remote

import androidx.paging.PagingSource
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class ReviewPagingSource(
    private val anbuApi: AnbuApi,
    private val id: String,
) : PagingSource<Int, Result>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val position = params.key ?: STARTING_PAGE_INDEX
            val response = anbuApi.getReview(id, position)
            val reviews = response.results

            LoadResult.Page(
                data = reviews,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (reviews.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

}