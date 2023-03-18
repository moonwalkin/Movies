package com.example.movies.domain

import javax.inject.Inject

class FetchPopularMovies @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke() = movieRepository.fetchPopularMovies()
}