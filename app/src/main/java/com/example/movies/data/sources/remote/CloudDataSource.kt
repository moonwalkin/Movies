package com.example.movies.data.sources.remote

import com.example.movies.data.CastResponse
import com.example.movies.data.MoviesResponse
import com.example.movies.data.Trailer

interface CloudDataSource {
    suspend fun fetchPopularMovies(): MoviesResponse
    suspend fun fetchNowPlayingMovies(): MoviesResponse
    suspend fun fetchMovieTrailerById(movieId: Int): Trailer
    suspend fun fetchActorsCast(movieId: Int): CastResponse
}