package com.example.core.presentation.viewState

import com.example.core.utils.NoNetworkException

enum class DataLoadingState {
    LOADING, SUCCESS, ERROR, NETWORK_ERROR;
}

fun <T> Result<T>.toDataLoadingState(): DataLoadingState {
    return when {
        this.isFailure -> {
            when (this.exceptionOrNull()) {
                is NoNetworkException -> DataLoadingState.NETWORK_ERROR
                else -> DataLoadingState.ERROR
            }
        }
        else -> DataLoadingState.SUCCESS
    }
}