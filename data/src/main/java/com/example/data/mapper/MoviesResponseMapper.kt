package com.example.data.mapper

import com.example.core.utils.Mapper
import com.example.domain.model.Movie
import com.example.network.getImageUrl
import com.example.network.model.MovieResponse
import com.example.network.model.MoviesResponse

class MoviesResponseMapper : Mapper<MoviesResponse, List<Movie>> {

    override fun map(from: MoviesResponse): List<Movie> {
        return from.results.map(::mapMovieResponse)
    }

    private fun mapMovieResponse(from: MovieResponse): Movie {
        return Movie(
            id = from.id ?: -1,
            title = from.title.orEmpty(),
            posterPath = from.posterPath?.let(::getImageUrl).orEmpty(),
            averageRating = from.averageRating ?: 0.0,
        )
    }
}