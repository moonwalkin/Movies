package com.example.movies.domain

import javax.inject.Inject

class FetchNowPlayingUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend fun invoke() = repository.fetchNowPlayingMovies()
}