package com.example.movies.data

import com.example.movies.domain.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val cloudDataSource: CloudDataSource) :
    MovieRepository {
    override suspend fun fetchPopularMovies(): List<Movie> {
        return try {
            cloudDataSource.fetchPopularMovies().movies
        } catch (e: Exception) {
            throw e
        }
    }
}