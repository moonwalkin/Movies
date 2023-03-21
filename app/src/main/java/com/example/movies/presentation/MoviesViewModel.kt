package com.example.movies.presentation

import androidx.lifecycle.viewModelScope
import com.example.movies.data.Cast
import com.example.movies.domain.Movie
import com.example.movies.domain.repository.CommonRepository
import com.example.movies.presentation.viewmodels.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main,
    private val repository: CommonRepository
) : BaseViewModel<List<Cast>>() {

    private val _trailerId = MutableSharedFlow<String>()
    val trailerId = _trailerId.asSharedFlow()

    fun addToFavorite(movie: Movie) = viewModelScope.launch(dispatcher) {
        repository.addMovieToFavorite(movie)
    }

    fun fetchMovieTrailer(id: Int) {
        viewModelScope.launch(dispatcher) {
            _trailerId.emit(repository.fetchMovieTrailerById(id))
        }
    }

    fun fetchActorsCast(id: Int) = viewModelScope.launch(dispatcher) {
        _items.emit(repository.fetchActorsCast(id))
    }
}