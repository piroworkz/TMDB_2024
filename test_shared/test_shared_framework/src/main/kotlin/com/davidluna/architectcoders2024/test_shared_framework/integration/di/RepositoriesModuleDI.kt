package com.davidluna.architectcoders2024.test_shared_framework.integration.di

import com.davidluna.architectcoders2024.auth_data_repositories.SessionDataRepository
import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.SessionRepository
import com.davidluna.architectcoders2024.core_data_repositories.datastore.LocalPreferencesDataRepository
import com.davidluna.architectcoders2024.core_domain.core_usecases.datastore.PreferencesRepository
import com.davidluna.architectcoders2024.media_data_repositories.MediaCatalogDataRepository
import com.davidluna.architectcoders2024.media_data_repositories.MovieDetailsDataRepository
import com.davidluna.architectcoders2024.media_domain.media_domain_usecases.MediaRepository
import com.davidluna.architectcoders2024.media_domain.media_domain_usecases.MovieDetailsRepository
import com.davidluna.architectcoders2024.videos_data_repositories.VideosDataRepository
import com.davidluna.architectcoders2024.videos_domain.videos_domain_usecases.VideosRepository

class RepositoriesModuleDI {
    val sessionRepository: SessionRepository by lazy {
        SessionDataRepository(
            FrameworkModuleDI().sessionDatasource,
            FrameworkModuleDI().preferencesDataSource
        )
    }
    val preferencesRepository: PreferencesRepository by lazy {
        LocalPreferencesDataRepository(FrameworkModuleDI().preferencesDataSource)
    }

    val mediaRepository: MediaRepository by lazy {
        MediaCatalogDataRepository(FrameworkModuleDI().mediaCatalogRemoteDatasource)
    }

    val movieDetailsRepository: MovieDetailsRepository by lazy {
        MovieDetailsDataRepository(FrameworkModuleDI().movieDetailsDataSource)
    }

    val videosRepository: VideosRepository by lazy {
        VideosDataRepository(FrameworkModuleDI().videosDataSource)
    }

}