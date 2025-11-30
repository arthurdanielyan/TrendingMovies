package com.example.network

import com.example.network.model.MovieDetailsResponse
import com.example.network.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface MoviesApi {

    private companion object {
        const val DEFAULT_TIME_WINDOW = "week"
    }

    @GET("trending/movie/{time_window}")
    suspend fun getTrendingMovies(
        @Path("time_window") timeWindow: String = DEFAULT_TIME_WINDOW
    ): MoviesResponse

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") movieId: Long
    ): MovieDetailsResponse
}