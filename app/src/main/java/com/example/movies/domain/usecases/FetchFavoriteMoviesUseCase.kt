package com.example.movies.domain.usecases

import com.example.movies.domain.repository.CommonRepository

class FetchFavoriteMoviesUseCase(private val repository: CommonRepository) {
    operator fun invoke() = repository.fetchFavoritesMovies()
}