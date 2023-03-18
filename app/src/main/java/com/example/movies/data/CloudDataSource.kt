package com.example.movies.data

interface CloudDataSource {
    suspend fun fetchPopularMovies(): MoviesResponse
}