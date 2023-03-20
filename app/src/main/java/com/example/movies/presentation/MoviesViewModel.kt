package com.example.movies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.domain.FetchNowPlayingUseCase
import com.example.movies.domain.FetchPopularMoviesUseCase
import com.example.movies.domain.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val fetchPopularMovies: FetchPopularMoviesUseCase,
    private val fetchLatestMoviesUseCase: FetchNowPlayingUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    val popularMovies = MutableSharedFlow<List<Movie>>()
    val nowPlayingMovies = MutableSharedFlow<List<Movie>>()

    fun fetchPopularMovies() = viewModelScope.launch(dispatcher) {
        popularMovies.emit(fetchPopularMovies.invoke())
    }

    fun fetchNowPlayingMovies() = viewModelScope.launch(dispatcher) {
        nowPlayingMovies.emit(fetchLatestMoviesUseCase.invoke())
    }

}