package com.example.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsResponse(
    @SerialName("id") val id: Long?,
    @SerialName("title") val title: String?,
    @SerialName("backdrop_path") val backdropPath: String?,
    @SerialName("vote_average") val rating: Double?,
    @SerialName("release_date") val releaseDate: String?,
    @SerialName("overview") val overview: String?,
    @SerialName("genres") val genres: List<Genre>?,
)

@Serializable
data class Genre(
    @SerialName("name") val name: String?,
)
