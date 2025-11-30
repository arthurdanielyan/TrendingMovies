package com.example.domain.di

import com.example.domain.usecase.GetMovieDetailsUseCase
import com.example.domain.usecase.GetMoviesUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {

    factoryOf(::GetMoviesUseCase)
    factoryOf(::GetMovieDetailsUseCase)
}
