package com.example.movies.domain

import com.example.movies.data.Cast
import com.example.movies.data.CastResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun fetchPopularMovies(): List<Movie>
    suspend fun fetchNowPlayingMovies(): List<Movie>
    suspend fun addMovieToFavorite(movie: Movie)
    suspend fun deleteMovieFromFavorite(movie: Movie)
    fun fetchFavoritesMovies(): Flow<List<Movie>>
    suspend fun fetchMovieTrailerById(id: Int): String

    suspend fun fetchActorsCast(id: Int): List<Cast>
}