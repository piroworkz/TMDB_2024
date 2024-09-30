package com.davidluna.tmdb.auth_domain.di

import com.davidluna.tmdb.auth_domain.data.SessionDataRepository
import com.davidluna.tmdb.auth_domain.usecases.CreateGuestSessionIdUseCase
import com.davidluna.tmdb.auth_domain.usecases.CreateRequestTokenUseCase
import com.davidluna.tmdb.auth_domain.usecases.CreateSessionUseCase
import com.davidluna.tmdb.auth_domain.usecases.GetUserAccountUseCase
import com.davidluna.tmdb.auth_domain.usecases.GuestSessionNotExpiredUseCase
import com.davidluna.tmdb.auth_domain.usecases.LoginViewModelUseCases
import com.davidluna.tmdb.auth_domain.usecases.SessionRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authDomainModule = module {
    factoryOf(::SessionDataRepository) bind SessionRepository::class
    factoryOf(::CreateGuestSessionIdUseCase)
    factoryOf(::CreateRequestTokenUseCase)
    factoryOf(::CreateSessionUseCase)
    factoryOf(::GetUserAccountUseCase)
    factoryOf(::GuestSessionNotExpiredUseCase)
    factoryOf(::LoginViewModelUseCases)
}