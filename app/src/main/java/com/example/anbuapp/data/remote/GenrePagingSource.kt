package com.example.anbuapp.data.remote

import androidx.paging.PagingSource
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class GenrePagingSource(
    private val anbuApi: AnbuApi,
    private val query: String?,
    private val genre: String?
) : PagingSource<Int, GenreX>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GenreX> {
        return try {
            val position = params.key ?: STARTING_PAGE_INDEX
            val response = if (query != null) {
                anbuApi.getGenres(position)
            } else if (genre != null) {
                anbuApi.getGenres(position)
            } else {
                anbuApi.getGenres(position)
            }
            val genres = response.genres

            LoadResult.Page(
                data = genres,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (genres.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

}