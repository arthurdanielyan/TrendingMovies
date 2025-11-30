package com.example.trendingmovies

import android.app.Application
import com.example.data.di.dataModule
import com.example.domain.di.domainModule
import com.example.featureMovieDetails.di.movieDetailsModule
import com.example.featureMovieList.di.movieListModule
import com.example.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class TrendingMoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(
                networkModule,
                dataModule,
                domainModule,
                movieListModule,
                movieDetailsModule,
            )
        }
    }
}