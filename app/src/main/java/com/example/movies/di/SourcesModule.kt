package com.example.movies.di

import com.example.movies.data.*
import com.example.movies.data.repository.CommonRepositoryImpl
import com.example.movies.data.sources.local.LocalDataSource
import com.example.movies.data.sources.local.LocalDataSourceImpl
import com.example.movies.data.sources.remote.CloudDataSource
import com.example.movies.data.sources.remote.CloudDataSourceImpl
import com.example.movies.domain.repository.CommonRepository
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
    fun bindLocalDataSource(localDataSource: LocalDataSourceImpl): LocalDataSource

    @Binds
    @Singleton
    fun bindRepository(moviesRepository: CommonRepositoryImpl): CommonRepository
}