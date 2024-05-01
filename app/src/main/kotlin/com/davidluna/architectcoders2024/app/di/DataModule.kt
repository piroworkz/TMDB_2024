package com.davidluna.architectcoders2024.app.di

import com.davidluna.architectcoders2024.app.data.local.datastore.LocalPreferencesDataSource
import com.davidluna.architectcoders2024.app.data.local.location.LocationDataSource
import com.davidluna.architectcoders2024.app.data.remote.datasources.ApiAuthenticationDatasource
import com.davidluna.architectcoders2024.app.data.remote.datasources.ApiMovieDetailsDataSource
import com.davidluna.architectcoders2024.app.data.remote.datasources.ApiMoviesDataSource
import com.davidluna.architectcoders2024.data.sources.AuthenticationDatasource
import com.davidluna.architectcoders2024.data.sources.PreferencesDataSource
import com.davidluna.architectcoders2024.data.sources.MovieDetailsDataSource
import com.davidluna.architectcoders2024.data.sources.MoviesDataSource
import com.davidluna.architectcoders2024.data.sources.RegionDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindAuthenticationDataSource(datasource: ApiAuthenticationDatasource): AuthenticationDatasource

    @Binds
    abstract fun bindLocalStorageDatastore(dataStore: LocalPreferencesDataSource): PreferencesDataSource

    @Binds
    abstract fun bindMovieDetailsDataSource(datasource: ApiMovieDetailsDataSource): MovieDetailsDataSource

    @Binds
    abstract fun bindMovieDataSource(datasource: ApiMoviesDataSource): MoviesDataSource

    @Binds
    abstract fun bindLocationDataSource(datasource: LocationDataSource): RegionDataSource

}