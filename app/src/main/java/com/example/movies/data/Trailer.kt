package com.example.movies.data


import com.squareup.moshi.Json

data class Trailer(
    @Json(name = "results")
    val results: List<TrailerKey>
)