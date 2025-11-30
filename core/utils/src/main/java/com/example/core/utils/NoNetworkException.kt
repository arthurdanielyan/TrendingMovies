package com.example.core.utils

import java.io.IOException

class NoNetworkException(override val cause: Throwable? = null) :
    IOException("No internet connection", cause)
