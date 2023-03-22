package com.example.movies.data.models


import com.squareup.moshi.Json

data class TrailerResponse(
    @Json(name = "results")
    val results: List<TrailerKeyDto>
)