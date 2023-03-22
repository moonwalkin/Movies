package com.example.movies.domain.repository

import com.example.movies.domain.State
import com.example.movies.domain.entity.Cast


interface CastActorsRepository {
    suspend fun fetchActorsCast(id: Int): State<List<Cast>>
}