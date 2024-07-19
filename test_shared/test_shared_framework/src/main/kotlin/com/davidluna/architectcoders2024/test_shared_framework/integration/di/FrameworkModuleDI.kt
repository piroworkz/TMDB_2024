package com.davidluna.architectcoders2024.test_shared_framework.integration.di

import com.davidluna.architectcoders2024.auth_data_framework.remote.ApiSessionDatasource
import com.davidluna.architectcoders2024.auth_data_repositories.SessionDatasource
import com.davidluna.architectcoders2024.core_data_framework.local.datastore.LocalPreferencesDataSource
import com.davidluna.architectcoders2024.core_data_repositories.datastore.PreferencesDataSource
import com.davidluna.architectcoders2024.media_data_framework.remote.datasources.MediaCatalogRemoteApi
import com.davidluna.architectcoders2024.media_data_framework.remote.datasources.MediaDetailsRemoteApi
import com.davidluna.architectcoders2024.media_data_repositories.MediaCatalogRemoteDatasource
import com.davidluna.architectcoders2024.media_data_repositories.MovieDetailsDataSource
import com.davidluna.architectcoders2024.videos_data_framework.remote.datasource.VideosRemoteApi
import com.davidluna.architectcoders2024.videos_data_repositories.VideosDataSource

class FrameworkModuleDI {
    val sessionDatasource: SessionDatasource by lazy {
        ApiSessionDatasource(NetworkModuleDI().sessionService)
    }
    val preferencesDataSource: PreferencesDataSource by lazy {
        LocalPreferencesDataSource(LocalModuleDI().dataStore)
    }

    val mediaCatalogRemoteDatasource: MediaCatalogRemoteDatasource by lazy {
        MediaCatalogRemoteApi(NetworkModuleDI().mediaCatalogService)
    }

    val movieDetailsDataSource: MovieDetailsDataSource by lazy {
        MediaDetailsRemoteApi(NetworkModuleDI().mediaDetailService)
    }

    val videosDataSource: VideosDataSource by lazy {
        VideosRemoteApi(NetworkModuleDI().videosService)
    }
}

