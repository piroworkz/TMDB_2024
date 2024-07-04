package com.davidluna.architectcoders2024.di.domain.usecases

import com.davidluna.architectcoders2024.auth_data_repositories.SessionDataRepository
import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.SessionRepository
import com.davidluna.architectcoders2024.core_data_repositories.datastore.LocalPreferencesDataRepository
import com.davidluna.architectcoders2024.core_data_repositories.location.RegionDataRepository
import com.davidluna.architectcoders2024.core_domain.core_usecases.datastore.LocalPreferencesRepository
import com.davidluna.architectcoders2024.core_domain.core_usecases.location.RegionRepository
import com.davidluna.architectcoders2024.media_data_repositories.MovieDetailsDataRepository
import com.davidluna.architectcoders2024.media_data_repositories.MediaCatalogDataRepository
import com.davidluna.architectcoders2024.videos_data_repositories.VideosDataRepository
import com.davidluna.architectcoders2024.videos_domain.videos_domain_usecases.VideosRepository
import com.davidluna.media_domain.media_domain_usecases.MovieDetailsRepository
import com.davidluna.media_domain.media_domain_usecases.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCasesModule {

    @Binds
    abstract fun bindAuthenticationRepository(repository: SessionDataRepository): SessionRepository

    @Binds
    abstract fun bindMoviesRepository(repository: MediaCatalogDataRepository): MoviesRepository

    @Binds
    abstract fun bindMoviesDetailRepository(repository: MovieDetailsDataRepository): MovieDetailsRepository

    @Binds
    abstract fun bindRegionRepository(repository: RegionDataRepository): RegionRepository

    @Binds
    abstract fun bindLocalSessionRepository(repository: LocalPreferencesDataRepository): LocalPreferencesRepository

    @Binds
    abstract fun bindVideoRepository(repository: VideosDataRepository): VideosRepository

}