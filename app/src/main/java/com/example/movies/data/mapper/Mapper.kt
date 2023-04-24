package com.example.movies.data.mapper

import com.example.movies.data.models.MovieDto
import com.example.movies.domain.entity.Movie

interface MovieMapper : BidirectionalMapper<MovieDto, Movie>

interface Mapper<T, E> {
    fun map(item: T): E
}

interface BidirectionalMapper<E, T> : Mapper<T, E> {
    fun reverseMap(item: E): T
}

