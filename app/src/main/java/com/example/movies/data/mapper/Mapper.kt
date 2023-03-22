package com.example.movies.data.mapper

interface Mapper<T, E> {
    fun mapToDomain(item: T): E
    fun mapToData(item: E): T
}