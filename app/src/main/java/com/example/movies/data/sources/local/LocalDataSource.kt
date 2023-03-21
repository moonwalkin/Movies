package com.example.movies.data.sources.local

import com.example.movies.data.MovieDto
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun fetchFavoriteMovies(): Flow<List<MovieDto>>
    suspend fun addMovie(movieDto: MovieDto)
    suspend fun deleteMovie(movieDto: MovieDto)
}