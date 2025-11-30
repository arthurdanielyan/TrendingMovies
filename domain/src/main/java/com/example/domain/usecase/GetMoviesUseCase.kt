package com.example.domain.usecase

import com.example.domain.MoviesRepository
import com.example.domain.model.Movie

class GetMoviesUseCase(
    private val repository: MoviesRepository
) {

    suspend operator fun invoke(): Result<List<Movie>> {
        return repository.getMovies()
    }
}