package com.example.data.mapper

import com.example.core.utils.Mapper
import com.example.domain.model.MovieDetails
import com.example.network.getImageUrl
import com.example.network.model.MovieDetailsResponse
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class MovieDetailsResponseMapper : Mapper<MovieDetailsResponse, MovieDetails> {

    private companion object {
        const val TARGET_DATE_FORMAT = "d MMMM yyyy"
    }

    override fun map(from: MovieDetailsResponse): MovieDetails {
        return MovieDetails(
            id = from.id ?: -1,
            title = from.title.orEmpty(),
            backdropUrl = from.backdropPath?.let(::getImageUrl).orEmpty(),
            rating = from.rating ?: 0.0,
            releaseDate = formatDate(from.releaseDate),
            overview = from.overview.orEmpty(),
            genres = from.genres?.map { it.name.orEmpty() }.orEmpty()
        )
    }


    private fun formatDate(date: String?): String {
        return runCatching {
            val date = LocalDate.parse(date, DateTimeFormatter.ISO_DATE)

            val formatter = DateTimeFormatter.ofPattern(TARGET_DATE_FORMAT, Locale.getDefault())
            return date.format(formatter)
        }.getOrDefault("")
    }
}