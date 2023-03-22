package com.example.movies.data.models


import com.example.movies.domain.entity.Cast
import com.squareup.moshi.Json

data class CastDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @field:Json(name = "profile_path")
    val profilePath: String
) {
    fun map() = Cast(
        id,
        name,
        profilePath
    )
}