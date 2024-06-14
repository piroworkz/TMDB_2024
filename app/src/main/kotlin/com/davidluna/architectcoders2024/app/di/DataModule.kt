package com.davidluna.architectcoders2024.app.di

import com.davidluna.architectcoders2024.app.data.local.datastore.LocalPreferencesDataSource
import com.davidluna.architectcoders2024.app.data.local.location.LocationDataSource
import com.davidluna.architectcoders2024.app.data.remote.datasources.ApiMovieDetailsDataSource
import com.davidluna.architectcoders2024.app.data.remote.datasources.ApiMoviesDataSource
import com.davidluna.architectcoders2024.app.data.remote.datasources.ApiSessionDatasource
import com.davidluna.architectcoders2024.data.sources.location.RegionDataSource
import com.davidluna.architectcoders2024.data.sources.movies.MovieDetailsDataSource
import com.davidluna.architectcoders2024.data.sources.movies.MoviesDataSource
import com.davidluna.architectcoders2024.data.sources.preferences.PreferencesDataSource
import com.davidluna.architectcoders2024.data.sources.session.SessionDatasource
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
    abstract fun bindMovieDetailsDataSource(datasource: ApiMovieDetailsDataSource): MovieDetailsDataSource

    @Binds
    abstract fun bindMovieDataSource(datasource: ApiMoviesDataSource): MoviesDataSource

    @Binds
    abstract fun bindLocationDataSource(datasource: LocationDataSource): RegionDataSource

}