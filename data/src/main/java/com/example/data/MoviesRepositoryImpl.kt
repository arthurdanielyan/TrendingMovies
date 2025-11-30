package com.example.data

import com.example.data.mapper.MovieDetailsResponseMapper
import com.example.data.mapper.MoviesResponseMapper
import com.example.domain.MoviesRepository
import com.example.domain.model.Movie
import com.example.domain.model.MovieDetails
import com.example.network.MoviesApi

internal class MoviesRepositoryImpl(
    private val moviesApi: MoviesApi,
    private val moviesResponseMapper: MoviesResponseMapper,
    private val movieDetailsResponseMapper: MovieDetailsResponseMapper,
) : MoviesRepository {

    override suspend fun getMovies(): Result<List<Movie>> {
        return runCatching {
            val result = moviesApi.getTrendingMovies()
            moviesResponseMapper.map(result)
        }
    }

    override suspend fun getMovieDetails(movieId: Long): Result<MovieDetails> {
        return runCatching {
            movieDetailsResponseMapper.map(moviesApi.getMovieDetails(movieId))
        }
    }
}