package com.davidluna.architectcoders2024.app.di

import com.davidluna.architectcoders2024.data.repositories.AuthenticationDataRepository
import com.davidluna.architectcoders2024.data.repositories.LocalSessionDataRepository
import com.davidluna.architectcoders2024.data.repositories.MovieDetailsDataRepository
import com.davidluna.architectcoders2024.data.repositories.MoviesDataRepository
import com.davidluna.architectcoders2024.data.repositories.RegionDataRepository
import com.davidluna.architectcoders2024.usecases.repositories.AuthenticationRepository
import com.davidluna.architectcoders2024.usecases.repositories.LocalSessionRepository
import com.davidluna.architectcoders2024.usecases.repositories.MovieDetailsRepository
import com.davidluna.architectcoders2024.usecases.repositories.MoviesRepository
import com.davidluna.architectcoders2024.usecases.repositories.RegionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCasesModule {

    @Binds
    abstract fun bindAuthenticationRepository(repository: AuthenticationDataRepository): AuthenticationRepository

    @Binds
    abstract fun bindMoviesRepository(repository: MoviesDataRepository): MoviesRepository

    @Binds
    abstract fun bindMoviesDetailRepository(repository: MovieDetailsDataRepository): MovieDetailsRepository

    @Binds
    abstract fun bindRegionRepository(repository: RegionDataRepository): RegionRepository

    @Binds
    abstract fun bindLocalSessionRepository(repository: LocalSessionDataRepository): LocalSessionRepository
}