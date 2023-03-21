package com.example.movies.domain.usecases

import com.example.movies.domain.repository.CommonRepository
import javax.inject.Inject

class FetchNowPlayingUseCase @Inject constructor(private val repository: CommonRepository) {
    suspend fun invoke() = repository.fetchNowPlayingMovies()
}