package com.arimukti.anbuapp.data.remote


import com.arimukti.anbuapp.BuildConfig
import com.arimukti.anbuapp.data.model.GenreResponse
import com.arimukti.anbuapp.data.model.MovieResponse
import com.arimukti.anbuapp.data.model.ReviewResponse
import com.arimukti.anbuapp.data.model.TrailerResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnbuApi {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val API_KEY = BuildConfig.MOVIEDB_API_KEY
    }

    @GET("movie/now_playing?api_key=$API_KEY")
    suspend fun getNowPlayingMovies(
        @Query("page") position: Int
    ): MovieResponse

    @GET("search/movie?api_key=$API_KEY")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int
    ): MovieResponse

    @GET("discover/movie?api_key=$API_KEY")
    suspend fun searchMoviesByGenre(
        @Query("with_genres") genre: String,
        @Query("page") page: Int
    ): MovieResponse

    @GET("genre/movie/list?api_key=$API_KEY")
    suspend fun getGenres(
        @Query("page") page: Int
    ): GenreResponse

    @GET("movie/{id}/reviews?api_key=$API_KEY")
    suspend fun getReview(
        @Path("id") id: String,
        @Query("page") page: Int
    ): ReviewResponse

    @GET("movie/{id}/videos?api_key=$API_KEY")
    suspend fun getTrailer(
        @Path("id") id: String,
        @Query("page") page: Int
    ): TrailerResponse

    @GET("movie/{id}/videos?api_key=$API_KEY")
    suspend fun getTrailers(
        @Path("id") id: String
    ): TrailerResponse
}