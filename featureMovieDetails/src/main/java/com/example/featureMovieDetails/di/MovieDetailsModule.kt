package com.example.featureMovieDetails.di

import com.example.featureMovieDetails.MovieDetailsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val movieDetailsModule = module {
    viewModelOf(::MovieDetailsViewModel)
}