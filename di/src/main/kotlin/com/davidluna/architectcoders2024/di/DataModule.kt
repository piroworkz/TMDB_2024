package com.davidluna.architectcoders2024.di

import com.davidluna.architectcoders2024.auth_data_framework.remote.ApiSessionDatasource
import com.davidluna.architectcoders2024.auth_data_repositories.SessionDatasource
import com.davidluna.architectcoders2024.core_data_framework.local.LocationDataSource
import com.davidluna.architectcoders2024.core_data_framework.local.datastore.LocalPreferencesDataSource
import com.davidluna.architectcoders2024.core_data_repositories.datastore.PreferencesDataSource
import com.davidluna.architectcoders2024.core_data_repositories.location.RegionDataSource
import com.davidluna.architectcoders2024.media_data_framework.remote.datasources.MediaCatalogRemoteApi
import com.davidluna.architectcoders2024.media_data_framework.remote.datasources.MediaDetailsRemoteApi
import com.davidluna.architectcoders2024.media_data_repositories.MediaCatalogRemoteDatasource
import com.davidluna.architectcoders2024.media_data_repositories.MovieDetailsDataSource
import com.davidluna.architectcoders2024.videos_data_framework.remote.datasource.ApiVideosDatasource
import com.davidluna.architectcoders2024.videos_data_repositories.VideosDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindAuthenticationDataSource(datasource: ApiSessionDatasource): SessionDatasource

    @Binds
    abstract fun bindLocalStorageDatastore(dataStore: LocalPreferencesDataSource): PreferencesDataSource

    @Binds
    abstract fun bindMovieDetailsDataSource(datasource: MediaDetailsRemoteApi): MovieDetailsDataSource

    @Binds
    abstract fun bindMovieDataSource(datasource: MediaCatalogRemoteApi): MediaCatalogRemoteDatasource

    @Binds
    abstract fun bindLocationDataSource(datasource: LocationDataSource): RegionDataSource

    @Binds
    abstract fun bindVideosDataSource(datasource: ApiVideosDatasource): VideosDataSource

}