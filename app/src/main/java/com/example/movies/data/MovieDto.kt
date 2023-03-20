package com.example.movies.data

import com.example.movies.domain.Movie

data class MovieDto(
    val adult: Boolean,
    val backdrop_path: String,
    val belongsToCollection: Any?,
    val genre_ids: List<Int>,
    val budget: Int?,
    val id: Int,
    val homepage: String?,
    val original_language: String,
    val original_title: String,
    val imdbId: String?,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val productionCompanies: List<Any>?,
    val productionCountries: List<Any>?,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val status: String?,
    val vote_count: Int,
    val revenue: Int?,
    val runtime: Int?,
    val spokenLanguages: List<Any>?,
    val tagline: String?
) {
    fun map() = Movie(
        adult,
        backdrop_path,
        genre_ids,
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
