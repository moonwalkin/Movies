package com.example.movies.domain.entity

import java.io.Serializable

data class Movie(
    val adult: Boolean,
    val id: Int,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double
) : Serializable