package com.example.movies.domain.usecases

import com.example.movies.domain.repository.CommonRepository
import javax.inject.Inject

class FetchPopularMoviesUseCase @Inject constructor(private val movieRepository: CommonRepository) {
    suspend operator fun invoke() = movieRepository.fetchPopularMovies()
}