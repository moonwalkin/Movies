package com.example.movies.data.sources.local

import com.example.movies.data.models.MovieDto
import com.example.movies.data.database.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dao: MovieDao
) : LocalDataSource {
    override fun fetchFavoriteMovies(): Flow<List<MovieDto>> {
        return dao.fetchFavoriteMovies()
    }

    override suspend fun addMovie(movieDto: MovieDto) {
        dao.addMovie(movieDto)
    }

    override suspend fun deleteMovie(movieDto: MovieDto) {
        dao.deleteMovie(movieDto)
    }
}