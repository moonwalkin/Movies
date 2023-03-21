package com.example.movies.domain.usecases

import com.example.movies.domain.Movie
import com.example.movies.domain.repository.CommonRepository
import javax.inject.Inject

class AddMovieToFavoriteUseCase @Inject constructor(private val repository: CommonRepository) {
    suspend operator fun invoke(movie: Movie) {
        repository.addMovieToFavorite(movie)
    }
}