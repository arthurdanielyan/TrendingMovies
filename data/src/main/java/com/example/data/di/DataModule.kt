package com.example.data.di

import com.example.data.MoviesRepositoryImpl
import com.example.data.mapper.MovieDetailsResponseMapper
import com.example.data.mapper.MoviesResponseMapper
import com.example.domain.MoviesRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataModule = module {

    factory<MoviesRepository> {
        MoviesRepositoryImpl(
            moviesApi = get(),
            moviesResponseMapper = get(),
            movieDetailsResponseMapper = get(),
        )
    }

    factoryOf(::MoviesResponseMapper)
    factoryOf(::MovieDetailsResponseMapper)
}