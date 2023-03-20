package com.example.movies.data

import javax.inject.Inject

class CloudDataSourceImpl @Inject constructor(private val movieService: MovieService) :
    CloudDataSource {
    override suspend fun fetchPopularMovies(): MoviesResponse {
        return try {
            movieService.fetchPopularMovies()
        } catch (e: Exception) {
            throw e
        }
    }
}