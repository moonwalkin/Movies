package com.example.movies.data.mapper

import com.example.movies.data.models.MovieDto
import com.example.movies.domain.entity.Movie
import javax.inject.Inject

class MapperImpl @Inject constructor() : Mapper<MovieDto, Movie> {
    override fun mapToDomain(item: MovieDto): Movie {
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

    override fun mapToData(item: Movie): MovieDto {
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