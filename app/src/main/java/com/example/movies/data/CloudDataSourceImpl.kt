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

    override suspend fun fetchNowPlayingMovies(): MoviesResponse {
        return try {
            movieService.fetchLatestMovies()
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchMovieTrailerById(movieId: Int): Trailer {
        return movieService.fetchTrailerById(movieId = movieId)
    }
}