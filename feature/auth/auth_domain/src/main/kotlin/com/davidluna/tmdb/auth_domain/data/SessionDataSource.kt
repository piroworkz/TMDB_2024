package com.davidluna.tmdb.auth_domain.data

import arrow.core.Either
import com.davidluna.tmdb.auth_domain.entities.session.LoginRequest
import com.davidluna.tmdb.auth_domain.entities.session.TokenResponse
import com.davidluna.tmdb.core_domain.entities.GuestSession
import com.davidluna.tmdb.core_domain.entities.SessionId
import com.davidluna.tmdb.core_domain.entities.UserAccount
import com.davidluna.tmdb.core_domain.entities.errors.AppError

interface SessionDataSource {
    suspend fun createRequestToken(): Either<AppError, TokenResponse>
    suspend fun createSessionId(loginRequest: LoginRequest): Either<AppError, com.davidluna.tmdb.core_domain.entities.SessionId>
    suspend fun getUserAccount(): Either<AppError, com.davidluna.tmdb.core_domain.entities.UserAccount>
    suspend fun createGuestSession(): Either<AppError, com.davidluna.tmdb.core_domain.entities.GuestSession>
}