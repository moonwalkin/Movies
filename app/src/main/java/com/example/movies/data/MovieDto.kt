package com.example.movies.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movies.domain.Movie

@Entity(tableName = "favorite_movies")
data class MovieDto(
    val adult: Boolean,
    val backdrop_path: String,
//    val genre_ids: List<Int>,
    val budget: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
) {
    fun map() = Movie(
        adult,
        backdrop_path,
        listOf(),
        id,
        original_language,
        original_title,
        overview,
        popularity,
        poster_path,
        release_date,
        title,
        video,
        vote_average,
        vote_count
    )
}
