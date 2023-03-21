package com.example.movies.data


import com.squareup.moshi.Json

data class TrailerKey(
    @Json(name = "key")
    val key: String,
)