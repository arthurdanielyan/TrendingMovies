package com.example.domain.model

data class MovieDetails(
    val id: Long = -1,
    val title: String = "",
    val backdropUrl: String = "",
    val rating: Double = 0.0,
    val releaseDate: String = "",
    val overview: String = "",
    val genres: List<String> = emptyList(),
)