package com.example.movies.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.movies.data.MovieDto
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * from favorite_movies")
    fun fetchFavoriteMovies(): Flow<List<MovieDto>>

    @Insert
    suspend fun addMovie(movieDto: MovieDto)

    @Delete
    suspend fun deleteMovie(movieDto: MovieDto)
}