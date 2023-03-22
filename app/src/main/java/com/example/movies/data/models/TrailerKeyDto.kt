package com.example.movies.data.models


import com.squareup.moshi.Json

data class TrailerKeyDto(
    @Json(name = "key")
    val key: String,
)