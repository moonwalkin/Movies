package com.example.movies.domain.usecases

import com.example.movies.domain.Movie
import com.example.movies.domain.repository.CommonRepository

class AddMovieToFavoriteUseCase(private val repository: CommonRepository) {
    suspend operator fun invoke(movie: Movie) {
        repository.addMovieToFavorite(movie)
    }
}