package com.example.movies.domain.usecases

import com.example.movies.domain.repository.CommonRepository
import javax.inject.Inject

class FetchActorsCastUseCase @Inject constructor(private val repository: CommonRepository) {
    suspend operator fun invoke(id: Int) = repository.fetchActorsCast(id)
}