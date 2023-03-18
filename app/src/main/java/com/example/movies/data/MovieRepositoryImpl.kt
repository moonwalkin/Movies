package com.example.movies.data

import com.example.movies.domain.MovieRepository

class MovieRepositoryImpl(private val cloudDataSource: CloudDataSource) : MovieRepository {
    override suspend fun fetchPopularMovies(): List<Movie> {
        return cloudDataSource.fetchPopularMovies().movies
    }
}