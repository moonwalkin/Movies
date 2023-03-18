package com.example.movies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.Movie
import com.example.movies.domain.FetchPopularMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val fetchPopularMovies: FetchPopularMovies,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    val movies = MutableSharedFlow<List<Movie>>()


    init {
        fetchMovies()
    }

    private fun fetchMovies() = viewModelScope.launch(dispatcher) {
        movies.emit(fetchPopularMovies())
    }

}