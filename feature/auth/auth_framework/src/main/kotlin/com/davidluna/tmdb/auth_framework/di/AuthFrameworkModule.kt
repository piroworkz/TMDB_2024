package com.davidluna.tmdb.auth_framework.di

import com.davidluna.tmdb.auth_domain.data.SessionDataRepository
import com.davidluna.tmdb.auth_domain.data.SessionDataSource
import com.davidluna.tmdb.auth_domain.usecases.SessionRepository
import com.davidluna.tmdb.auth_framework.data.remote.RemoteSessionDataSource
import com.davidluna.tmdb.auth_framework.data.remote.SessionService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

val authFrameworkModule = module {
    singleOf(::provideAuthenticationService)
    singleOf(::RemoteSessionDataSource) bind SessionDataSource::class
    singleOf(::SessionDataRepository) bind SessionRepository::class
}

private fun provideAuthenticationService(retrofit: Retrofit): SessionService =
    retrofit.create(SessionService::class.java)