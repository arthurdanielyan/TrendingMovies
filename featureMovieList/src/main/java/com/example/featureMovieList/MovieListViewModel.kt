package com.example.featureMovieList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.presentation.viewState.DataLoadingState
import com.example.core.presentation.viewState.toDataLoadingState
import com.example.domain.model.Movie
import com.example.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class MovieListViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
) : ViewModel() {

    private val refreshEvent = MutableSharedFlow<Unit>(
        replay = 1,
    )

    private val _uiEffect = Channel<MovieListUiEffect>(
        capacity = Channel.BUFFERED
    )
    val uiEffect = _uiEffect.receiveAsFlow()

    private val _dataLoadingState = MutableStateFlow(DataLoadingState.LOADING)
    val dataLoadingState = _dataLoadingState.asStateFlow()

    private val _movies = MutableStateFlow(emptyList<Movie>())
    val movies = _movies.asStateFlow()

    fun init() {
        getMovies()
        onRetry()
    }

    fun onRetry() {
        refreshEvent.tryEmit(Unit)
    }

    fun onMovieClick(movie: Movie) {
        _uiEffect.trySend(
            MovieListUiEffect.NavigateToDetails(movie.id)
        )
    }

    private fun getMovies() {
        viewModelScope.launch {
            refreshEvent.collectLatest {
                _dataLoadingState.update { DataLoadingState.LOADING }
                getMoviesUseCase().let { result ->
                    _movies.update { result.getOrDefault(emptyList()) }
                    _dataLoadingState.update { result.toDataLoadingState() }
                }
            }
        }
    }
}