package com.example.movies.data


import com.squareup.moshi.Json

data class CastResponse(
    @Json(name = "cast")
    val cast: List<Cast>,
)