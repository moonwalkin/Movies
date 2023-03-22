package com.example.movies.domain.repository

import com.example.movies.domain.State
import com.example.movies.domain.entity.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun fetchPopularMovies(): State<List<Movie>>
    suspend fun fetchNowPlayingMovies(): State<List<Movie>>
    suspend fun addMovieToFavorite(movie: Movie)
    suspend fun deleteMovieFromFavorite(movie: Movie)
    fun fetchFavoritesMovies(): Flow<State<List<Movie>>>
    suspend fun fetchMovieTrailerById(id: Int): State<String>
}