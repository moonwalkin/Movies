package com.example.movies.data

interface CloudDataSource {
    suspend fun fetchPopularMovies(): MoviesResponse
    suspend fun fetchNowPlayingMovies(): MoviesResponse
    suspend fun fetchMovieTrailerById(movieId: Int): Trailer
}