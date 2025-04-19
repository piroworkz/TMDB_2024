package com.davidluna.tmdb.videos_framework.di

import com.davidluna.tmdb.videos_domain.data.VideosDataRepository
import com.davidluna.tmdb.videos_domain.data.VideosDataSource
import com.davidluna.tmdb.videos_domain.usecases.VideosRepository
import com.davidluna.tmdb.videos_framework.data.remote.datasource.VideosRemoteApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class VideosDataModule {

    @Binds
    abstract fun bindVideosDataSource(datasource: VideosRemoteApi): VideosDataSource

    @Binds
    abstract fun bindVideoRepository(repository: VideosDataRepository): VideosRepository
}