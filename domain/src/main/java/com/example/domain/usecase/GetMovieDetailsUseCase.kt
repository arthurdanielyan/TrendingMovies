package com.example.domain.usecase

import com.example.domain.MoviesRepository
import com.example.domain.model.Movie
import com.example.domain.model.MovieDetails

class GetMovieDetailsUseCase(
    private val repository: MoviesRepository
) {

    suspend operator fun invoke(movieId: Long): Result<MovieDetails> {
        return repository.getMovieDetails(movieId)
    }
}