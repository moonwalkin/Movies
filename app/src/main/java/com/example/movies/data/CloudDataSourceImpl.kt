package com.example.movies.data

class CloudDataSourceImpl(private val movieService: MovieService) : CloudDataSource {
    override suspend fun fetchPopularMovies(): MoviesResponse {
        return movieService.fetchPopularMovies()
    }
}