package com.example.featureMovieDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.presentation.viewState.DataLoadingState
import com.example.core.presentation.viewState.toDataLoadingState
import com.example.domain.model.MovieDetails
import com.example.domain.usecase.GetMovieDetailsUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
) : ViewModel() {

    private val refreshEvent = MutableSharedFlow<Unit>(
        replay = 1,
    )

    private val _movieDetails = MutableStateFlow(MovieDetails())
    val movieDetails = _movieDetails.asStateFlow()

    private val _dataLoadingState = MutableStateFlow(DataLoadingState.LOADING)
    val dataLoadingState = _dataLoadingState.asStateFlow()

    fun init(movieId: Long) {
        getMovieDetails(movieId)
        onRetry()
    }

    fun onRetry() {
        refreshEvent.tryEmit(Unit)
    }

    private fun getMovieDetails(movieId: Long) {
        viewModelScope.launch {
            refreshEvent.collectLatest {
                getMovieDetailsUseCase(movieId).let { result ->
                    _movieDetails.update { result.getOrDefault(MovieDetails()) }
                    _dataLoadingState.update { result.toDataLoadingState() }
                }
            }
        }
    }
}