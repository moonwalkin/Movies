package com.example.movies.domain.repository

import com.example.movies.data.Cast

interface CastActorsRepository {
    suspend fun fetchActorsCast(id: Int): List<Cast>
}