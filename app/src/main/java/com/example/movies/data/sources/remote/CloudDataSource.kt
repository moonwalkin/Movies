package com.example.movies.data.sources.remote

import com.example.movies.data.models.CastResponse
import com.example.movies.data.models.MoviesResponse
import com.example.movies.data.models.TrailerResponse

interface CloudDataSource {
    suspend fun fetchPopularMovies(): MoviesResponse
    suspend fun fetchNowPlayingMovies(): MoviesResponse
    suspend fun fetchMovieTrailerById(movieId: Int): TrailerResponse
    suspend fun fetchActorsCast(movieId: Int): CastResponse
}