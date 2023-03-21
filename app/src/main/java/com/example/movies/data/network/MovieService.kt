package com.example.movies.data.network

import com.example.movies.data.CastResponse
import com.example.movies.data.MoviesResponse
import com.example.movies.data.Trailer
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {
    @GET("popular")
    suspend fun fetchPopularMovies(): MoviesResponse

    @GET("now_playing")
    suspend fun fetchLatestMovies(): MoviesResponse

    @GET("{movie_id}/videos")
    suspend fun fetchTrailerById(@Path("movie_id") movieId: Int): Trailer

    @GET("{movie_id}/credits")
    suspend fun fetchActorsCast(@Path("movie_id") movieId: Int): CastResponse
}