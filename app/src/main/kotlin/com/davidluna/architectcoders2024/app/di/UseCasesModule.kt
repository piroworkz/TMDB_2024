package com.davidluna.architectcoders2024.app.di

import com.davidluna.architectcoders2024.data.repositories.location.RegionDataRepository
import com.davidluna.architectcoders2024.data.repositories.movies.MovieDetailsDataRepository
import com.davidluna.architectcoders2024.data.repositories.movies.MoviesDataRepository
import com.davidluna.architectcoders2024.data.repositories.preferences.LocalPreferencesDataRepository
import com.davidluna.architectcoders2024.data.repositories.session.SessionDataRepository
import com.davidluna.architectcoders2024.usecases.repositories.LocalPreferencesRepository
import com.davidluna.architectcoders2024.usecases.repositories.MovieDetailsRepository
import com.davidluna.architectcoders2024.usecases.repositories.MoviesRepository
import com.davidluna.architectcoders2024.usecases.repositories.RegionRepository
import com.davidluna.architectcoders2024.usecases.repositories.SessionRepository
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
    abstract fun bindMoviesRepository(repository: MoviesDataRepository): MoviesRepository

    @Binds
    abstract fun bindMoviesDetailRepository(repository: MovieDetailsDataRepository): MovieDetailsRepository

    @Binds
    abstract fun bindRegionRepository(repository: RegionDataRepository): RegionRepository

    @Binds
    abstract fun bindLocalSessionRepository(repository: LocalPreferencesDataRepository): LocalPreferencesRepository

}