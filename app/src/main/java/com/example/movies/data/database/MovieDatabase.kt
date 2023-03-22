package com.example.movies.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movies.data.models.MovieDto

@Database(
    entities = [
        MovieDto::class
    ],
    version = 1
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}