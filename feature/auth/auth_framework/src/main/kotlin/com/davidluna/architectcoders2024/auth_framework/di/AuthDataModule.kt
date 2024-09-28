package com.davidluna.tmdb.auth_framework.di

import com.davidluna.tmdb.auth_domain.data.SessionDataRepository
import com.davidluna.tmdb.auth_domain.data.SessionDataSource
import com.davidluna.tmdb.auth_domain.usecases.SessionRepository
import com.davidluna.tmdb.auth_framework.data.remote.RemoteSessionDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthDataModule {
    @Binds
    abstract fun bindAuthenticationDataSource(datasource: RemoteSessionDataSource): SessionDataSource

    @Binds
    abstract fun bindAuthenticationRepository(repository: SessionDataRepository): SessionRepository
}