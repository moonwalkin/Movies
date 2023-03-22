package com.example.movies.data.sources.remote

import com.example.movies.data.models.CastResponse
import com.example.movies.data.network.MovieService
import com.example.movies.data.models.MoviesResponse
import com.example.movies.data.models.TrailerResponse
import javax.inject.Inject

class CloudDataSourceImpl @Inject constructor(private val movieService: MovieService) :
    CloudDataSource {
    override suspend fun fetchPopularMovies(page: Int): MoviesResponse {
        return try {
            movieService.fetchPopularMovies(page)
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
        return try {
            movieService.fetchTrailerById(movieId = movieId)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchActorsCast(movieId: Int): CastResponse {
        return try {
            movieService.fetchActorsCast(movieId)
        } catch (e: Exception) {
            throw e
        }
    }
}