package com.example.movies.data.mapper

import com.example.movies.data.models.MovieDto
import com.example.movies.domain.entity.Movie
import javax.inject.Inject

class MovieMapperImpl @Inject constructor() : MovieMapper {
    override fun reverseMap(item: MovieDto): Movie {
        return Movie(
            item.adult,
            item.id,
            item.overview,
            item.posterPath,
            item.releaseDate,
            item.title,
            item.voteAverage
        )
    }

    override fun map(item: Movie): MovieDto {
        return MovieDto(
            item.adult,
            item.id,
            item.overview,
            item.posterPath,
            item.releaseDate,
            item.title,
            item.voteAverage
        )
    }
}