package com.example.movies.data.sources.remote

import com.example.movies.data.models.CastResponse
import com.example.movies.data.network.MovieService
import com.example.movies.data.models.MoviesResponse
import com.example.movies.data.models.TrailerResponse
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

    override suspend fun fetchMovieTrailerById(movieId: Int): TrailerResponse {
        return movieService.fetchTrailerById(movieId = movieId)
    }

    override suspend fun fetchActorsCast(movieId: Int): CastResponse {
        return movieService.fetchActorsCast(movieId)
    }
}