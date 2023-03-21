package com.example.movies.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.movies.domain.Movie
import com.example.movies.domain.usecases.FetchPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val fetchPopularMoviesUseCase: FetchPopularMoviesUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : BaseViewModel<List<Movie>>() {

    fun fetchPopularMovies() = viewModelScope.launch(dispatcher) {
        _items.emit(fetchPopularMoviesUseCase())
    }
}