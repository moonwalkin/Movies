package com.example.movies.di

import com.example.movies.data.mapper.Mapper
import com.example.movies.data.mapper.MapperImpl
import com.example.movies.data.models.MovieDto
import com.example.movies.domain.entity.Movie
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface MapperModule {

    @Binds
    @Singleton
    fun bindMapper(mapper: MapperImpl): Mapper<MovieDto, Movie>
}