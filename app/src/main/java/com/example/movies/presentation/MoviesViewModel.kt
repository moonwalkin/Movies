package com.example.movies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.Cast
import com.example.movies.domain.Movie
import com.example.movies.domain.repository.CommonRepository
import com.example.movies.domain.usecases.FetchNowPlayingUseCase
import com.example.movies.domain.usecases.FetchPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val fetchPopularMovies: FetchPopularMoviesUseCase,
    private val fetchLatestMoviesUseCase: FetchNowPlayingUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main,
    private val repository: CommonRepository
) : ViewModel() {

    val popularMovies = MutableSharedFlow<List<Movie>>()
    val nowPlayingMovies = MutableSharedFlow<List<Movie>>()
    val favoriteMovies = MutableSharedFlow<List<Movie>>()
    val actors = MutableSharedFlow<List<Cast>>()
    val trailerId = MutableSharedFlow<String>()

    fun fetchPopularMovies() = viewModelScope.launch(dispatcher) {
        popularMovies.emit(fetchPopularMovies.invoke())
    }

    fun fetchNowPlayingMovies() = viewModelScope.launch(dispatcher) {
        nowPlayingMovies.emit(fetchLatestMoviesUseCase.invoke())
    }

    fun addToFavorite(movie: Movie) = viewModelScope.launch(dispatcher) {
        repository.addMovieToFavorite(movie)
    }

    fun deleteFromFavorite(movie: Movie) = viewModelScope.launch(dispatcher) {
        repository.deleteMovieFromFavorite(movie)
    }

    fun fetchFavoriteMovies() = repository.fetchFavoritesMovies().map {
        it.sortedBy { movie ->
            movie.voteAverage
        }.reversed()
    }

    fun fetchMovieTrailer(id: Int) {
        viewModelScope.launch(dispatcher) {
            trailerId.emit(repository.fetchMovieTrailerById(id))
        }
    }

    fun fetchActorsCast(id: Int) = viewModelScope.launch(dispatcher) {
        actors.emit(repository.fetchActorsCast(id))
    }
}