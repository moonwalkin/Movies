package com.example.movies.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movies.domain.Movie
import com.squareup.moshi.Json

@Entity(tableName = "favorite_movies")
data class MovieDto(
    @field:Json(name = "adult")
    val adult: Boolean,
    @PrimaryKey(autoGenerate = true)
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "overview")
    val overview: String,
    @field:Json(name = "poster_path")
    val posterPath: String,
    @field:Json(name = "release_date")
    val releaseDate: String,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "vote_average")
    val voteAverage: Double
) {
    fun map() = Movie(
        adult,
        id,
        overview,
        posterPath,
        releaseDate,
        title,
        voteAverage
    )
}
