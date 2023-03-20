package com.example.movies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.Trailer
import com.example.movies.domain.FetchNowPlayingUseCase
import com.example.movies.domain.FetchPopularMoviesUseCase
import com.example.movies.domain.Movie
import com.example.movies.domain.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val fetchPopularMovies: FetchPopularMoviesUseCase,
    private val fetchLatestMoviesUseCase: FetchNowPlayingUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main,
    private val repository: MovieRepository
) : ViewModel() {

    val popularMovies = MutableSharedFlow<List<Movie>>()
    val nowPlayingMovies = MutableSharedFlow<List<Movie>>()
    val favoriteMovies = MutableSharedFlow<List<Movie>>()

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
            movie.vote_average
        }.reversed()
    }

    fun fetchMovieTrailer(id: Int): String {
        var key = ""
        viewModelScope.launch(dispatcher) {
            key = repository.fetchMovieTrailerById(id)
        }
        return key
    }
}