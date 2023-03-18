package com.example.movies.data

import com.example.movies.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("popular")
    suspend fun fetchPopularMovies(@Query("api_key") api_key: String = BuildConfig.API_KEY): MoviesResponse
}