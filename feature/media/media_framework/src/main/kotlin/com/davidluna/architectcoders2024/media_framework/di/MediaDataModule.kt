package com.davidluna.architectcoders2024.media_framework.di

import com.davidluna.architectcoders2024.media_domain.data.MediaCatalogDataRepository
import com.davidluna.architectcoders2024.media_domain.data.MediaCatalogRemoteDatasource
import com.davidluna.architectcoders2024.media_domain.data.MovieDetailsDataRepository
import com.davidluna.architectcoders2024.media_domain.data.MovieDetailsDataSource
import com.davidluna.architectcoders2024.media_domain.usecases.MediaRepository
import com.davidluna.architectcoders2024.media_domain.usecases.MovieDetailsRepository
import com.davidluna.architectcoders2024.media_framework.data.remote.datasources.MediaCatalogRemoteApi
import com.davidluna.architectcoders2024.media_framework.data.remote.datasources.MediaDetailsRemoteApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MediaDataModule {
    @Binds
    abstract fun bindMovieDetailsDataSource(datasource: MediaDetailsRemoteApi): MovieDetailsDataSource

    @Binds
    abstract fun bindMovieDataSource(datasource: MediaCatalogRemoteApi): MediaCatalogRemoteDatasource

    @Binds
    abstract fun bindMoviesRepository(repository: MediaCatalogDataRepository): MediaRepository

    @Binds
    abstract fun bindMoviesDetailRepository(repository: MovieDetailsDataRepository): MovieDetailsRepository
}