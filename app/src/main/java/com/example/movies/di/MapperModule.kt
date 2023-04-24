package com.example.movies.di

import com.example.movies.data.mapper.MovieMapper
import com.example.movies.data.mapper.MovieMapperImpl
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
    fun bindMapper(mapper: MovieMapperImpl): MovieMapper
}