package com.example.featureMovieList.di

import com.example.featureMovieList.MovieListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val movieListModule = module {

    viewModelOf(::MovieListViewModel)
}
