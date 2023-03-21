package com.example.movies.data.network

import com.example.movies.BuildConfig
import com.example.movies.data.CastResponse
import com.example.movies.data.MoviesResponse
import com.example.movies.data.Trailer
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("popular")
    suspend fun fetchPopularMovies(@Query("api_key") api_key: String = BuildConfig.API_KEY): MoviesResponse

    @GET("now_playing")
    suspend fun fetchLatestMovies(@Query("api_key") api_key: String = BuildConfig.API_KEY): MoviesResponse

    @GET("{movie_id}/videos")
    suspend fun fetchTrailerById(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): Trailer

    @GET("{movie_id}/credits")
    suspend fun fetchActorsCast(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): CastResponse
}