package com.example.movies.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.movies.domain.State
import com.example.movies.domain.entity.Movie
import com.example.movies.domain.usecases.DeleteMovieFromFavoriteUseCase
import com.example.movies.domain.usecases.FetchFavoriteMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteMoviesViewModel @Inject constructor(
    private val fetchMovieFromFavoriteUseCase: FetchFavoriteMoviesUseCase,
    private val deleteMovieFromFavoriteUseCase: DeleteMovieFromFavoriteUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : BaseViewModel<List<Movie>>() {

    fun deleteFromFavorite(movie: Movie) = viewModelScope.launch(dispatcher) {
        deleteMovieFromFavoriteUseCase(movie)
    }

    fun fetchFavoriteMovies() {
        viewModelScope.launch(dispatcher) {
            _items.emit(State.Loading())
            fetchMovieFromFavoriteUseCase().collect {
                _items.emit(it)
            }
        }
    }
}