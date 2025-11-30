package com.example.featureMovieList

internal sealed interface MovieListUiEffect {

    data class NavigateToDetails(
        val movieId: Long
    ): MovieListUiEffect
}