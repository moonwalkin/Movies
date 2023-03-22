package com.example.movies.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.movies.domain.State
import com.example.movies.domain.entity.Cast
import com.example.movies.domain.entity.Movie
import com.example.movies.domain.usecases.AddMovieToFavoriteUseCase
import com.example.movies.domain.usecases.FetchActorsCastUseCase
import com.example.movies.domain.usecases.FetchMovieTrailerByIdUseCase
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
    private val fetchMovieTrailerByIdUseCase: FetchMovieTrailerByIdUseCase,
    private val addMovieToFavoriteUseCase: AddMovieToFavoriteUseCase,
    private val fetchActorsCastUseCase: FetchActorsCastUseCase
) : BaseViewModel<List<Cast>>() {

    private val _trailerId = MutableSharedFlow<State<String>>()
    val trailerId = _trailerId.asSharedFlow()

    fun addToFavorite(movie: Movie) = viewModelScope.launch(dispatcher) {
        addMovieToFavoriteUseCase(movie)
    }

    fun fetchMovieTrailer(id: Int) {
        viewModelScope.launch(dispatcher) {
            _trailerId.emit(State.Loading())
            _trailerId.emit(fetchMovieTrailerByIdUseCase(id))
        }
    }

    fun fetchActorsCast(id: Int) = viewModelScope.launch(dispatcher) {
        _items.emit(State.Loading())
        _items.emit(fetchActorsCastUseCase(id))
    }
}