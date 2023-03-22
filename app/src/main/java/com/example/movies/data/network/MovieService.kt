package com.example.movies.data.network

import com.example.movies.data.models.CastResponse
import com.example.movies.data.models.MoviesResponse
import com.example.movies.data.models.TrailerResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/popular")
    suspend fun fetchPopularMovies(@Query("page") page: Int): MoviesResponse

    @GET("movie/now_playing")
    suspend fun fetchLatestMovies(): MoviesResponse

    @GET("movie/{movie_id}/videos")
    suspend fun fetchTrailerById(@Path("movie_id") movieId: Int): TrailerResponse

    @GET("movie/{movie_id}/credits")
    suspend fun fetchActorsCast(@Path("movie_id") movieId: Int): CastResponse
}