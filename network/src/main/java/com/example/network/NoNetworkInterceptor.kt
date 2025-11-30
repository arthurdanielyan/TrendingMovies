package com.example.network

import com.example.core.utils.NoNetworkException
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class NoNetworkInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return try {
            chain.proceed(chain.request())
        } catch (cause: IOException) {
            when (cause) {
                is UnknownHostException,
                is ConnectException,
                is SocketTimeoutException
                    -> throw NoNetworkException(cause)

                else -> throw cause
            }
        }
    }
}
