package com.davidluna.tmdb.auth_domain.usecases

import arrow.core.Either
import com.davidluna.tmdb.auth_domain.entities.session.LoginRequest
import com.davidluna.tmdb.auth_domain.entities.session.TokenResponse
import com.davidluna.tmdb.core_domain.entities.Session
import com.davidluna.tmdb.core_domain.entities.UserAccount
import com.davidluna.tmdb.core_domain.entities.errors.AppError

interface SessionRepository {
    suspend fun createRequestToken(): Either<AppError, TokenResponse>
    suspend fun createSessionId(loginRequest: LoginRequest): Either<AppError, com.davidluna.tmdb.core_domain.entities.Session>
    suspend fun getUserAccount(): Either<AppError, com.davidluna.tmdb.core_domain.entities.UserAccount>
    suspend fun createGuestSession(): Either<AppError, com.davidluna.tmdb.core_domain.entities.Session>
}