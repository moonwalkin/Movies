package com.example.movies.data

import com.squareup.moshi.Json

data class MoviesResponse(
    @field:Json(name = "page")
    val page: Int,
    @field:Json(name = "results")
    val movies: List<MovieDto>,
    @field:Json(name = "total_pages")
    val totalPages: Int,
    @field:Json(name = "total_results")
    val totalResults: Int
)