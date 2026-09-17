package com.example.network

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    private companion object {
        const val API_KEY_HEADER_NAME = "Authorization"
        const val API_KEY = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxN2Q1YWI3NjU3OTFmMmIxZTAyYmE2MjE1NTQ3OWQxZiIsIm5iZiI6MTc4OTYzOTM2NS4yMDMsInN1YiI6IjZhYWJiYWM1NGZkMWM1M2Y0OGU3ZDQ2YSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.tf0gyQWpfQZtAxsHS15W58_ePop297h6OpkyRL3zBog"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .header(API_KEY_HEADER_NAME, API_KEY)
            .build()

        return chain.proceed(request)
    }
}
