package com.example.movies.domain

import com.example.movies.data.Movie

interface MovieRepository {

    suspend fun fetchPopularMovies(): List<Movie>
}