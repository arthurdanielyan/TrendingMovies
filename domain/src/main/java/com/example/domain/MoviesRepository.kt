package com.example.domain

import com.example.domain.model.Movie
import com.example.domain.model.MovieDetails

interface MoviesRepository {

    suspend fun getMovies(): Result<List<Movie>>

    suspend fun getMovieDetails(movieId: Long): Result<MovieDetails>
}
