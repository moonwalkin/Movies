package com.example.movies.data.models


import com.squareup.moshi.Json

data class CastResponse(
    @Json(name = "cast")
    val cast: List<CastDto>,
)