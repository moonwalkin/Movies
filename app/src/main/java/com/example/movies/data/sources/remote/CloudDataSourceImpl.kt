package com.example.movies.data.sources.remote

import com.example.movies.data.CastResponse
import com.example.movies.data.network.MovieService
import com.example.movies.data.MoviesResponse
import com.example.movies.data.Trailer
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

    override suspend fun fetchActorsCast(movieId: Int): CastResponse {
        return movieService.fetchActorsCast(movieId)
    }
}