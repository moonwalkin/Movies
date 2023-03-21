package com.example.movies.domain.usecases

import com.example.movies.domain.repository.CommonRepository

class FetchMovieTrailerByIdUseCase(private val repository: CommonRepository) {
    suspend operator fun invoke(id: Int) = repository.fetchMovieTrailerById(id)
}