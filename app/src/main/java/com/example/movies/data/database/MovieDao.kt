package com.example.movies.data.database

import androidx.room.*
import com.example.movies.data.models.MovieDto
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * from favorite_movies")
    fun fetchFavoriteMovies(): Flow<List<MovieDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movieDto: MovieDto)

    @Delete
    suspend fun deleteMovie(movieDto: MovieDto)
}

