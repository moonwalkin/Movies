package com.example.movies.domain.usecases

import com.example.movies.domain.repository.CommonRepository
import javax.inject.Inject

class FetchFavoriteMoviesUseCase @Inject constructor(private val repository: CommonRepository) {
    operator fun invoke() = repository.fetchFavoritesMovies()
}