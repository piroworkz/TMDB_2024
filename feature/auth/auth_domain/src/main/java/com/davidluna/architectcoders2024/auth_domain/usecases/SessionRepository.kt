package com.davidluna.architectcoders2024.auth_domain.usecases

import arrow.core.Either
import com.davidluna.architectcoders2024.auth_domain.entities.session.LoginRequest
import com.davidluna.architectcoders2024.auth_domain.entities.session.TokenResponse
import com.davidluna.architectcoders2024.core_domain.entities.Session
import com.davidluna.architectcoders2024.core_domain.entities.UserAccount
import com.davidluna.architectcoders2024.core_domain.entities.errors.AppError

interface SessionRepository {
    suspend fun createRequestToken(): Either<AppError, TokenResponse>
    suspend fun createSessionId(loginRequest: LoginRequest): Either<AppError, Session>
    suspend fun getUserAccount(): Either<AppError, UserAccount>
    suspend fun createGuestSession(): Either<AppError, Session>
}