package com.example.network

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    private companion object {
        const val API_KEY_HEADER_NAME = "Authorization"
        const val API_KEY = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5NDY1ZGIzYzIyN2ZlMzcwNjIzN2I0ODQxMDQwMTY4NCIsIm5iZiI6MTc2NDQ0NDk5OS42MzUsInN1YiI6IjY5MmI0YjQ3NTlkMzkyZTMwZTZjZjcxZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.yizIkbK7CS1XnjsYut9dukT83sTEMg5wUhWZGYI2x0k"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .header(API_KEY_HEADER_NAME, API_KEY)
            .build()

        return chain.proceed(request)
    }
}
