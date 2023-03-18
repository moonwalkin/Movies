package com.example.movies.di

import com.example.movies.data.CloudDataSource
import com.example.movies.data.CloudDataSourceImpl
import com.example.movies.data.MovieRepositoryImpl
import com.example.movies.domain.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface SourcesModule {

    @Binds
    @Singleton
    fun bindCloudDataSource(cloudDataSource: CloudDataSourceImpl): CloudDataSource

    @Binds
    @Singleton
    fun bindRepository(moviesRepository: MovieRepositoryImpl): MovieRepository
}