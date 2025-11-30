package com.example.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(
    @SerialName("results") val results: List<MovieResponse>
)

@Serializable
data class MovieResponse(
    @SerialName("id") val id: Long?,
    @SerialName("title") val title: String?,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("vote_average") val averageRating: Double?,
)