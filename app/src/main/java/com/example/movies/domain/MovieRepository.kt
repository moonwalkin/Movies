package com.example.movies.domain

interface MovieRepository {
    suspend fun fetchPopularMovies(): List<Movie>
    suspend fun fetchNowPlayingMovies(): List<Movie>
}