package com.example.movies.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.movies.domain.State
import com.example.movies.domain.entity.Movie
import com.example.movies.domain.usecases.FetchNowPlayingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NowPlayingMoviesViewModel @Inject constructor(
    private val fetchNowPlayingMoviesUseCase: FetchNowPlayingUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : BaseViewModel<List<Movie>>() {

    fun fetchNowPlayingMovies() = viewModelScope.launch(dispatcher) {
        _items.emit(State.Loading())
        _items.emit(fetchNowPlayingMoviesUseCase())
    }
}