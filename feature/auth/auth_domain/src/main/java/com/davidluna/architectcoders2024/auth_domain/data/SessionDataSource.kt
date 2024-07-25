package com.davidluna.architectcoders2024.auth_domain.data

import arrow.core.Either
import com.davidluna.architectcoders2024.auth_domain.entities.session.LoginRequest
import com.davidluna.architectcoders2024.auth_domain.entities.session.TokenResponse
import com.davidluna.architectcoders2024.core_domain.entities.GuestSession
import com.davidluna.architectcoders2024.core_domain.entities.SessionId
import com.davidluna.architectcoders2024.core_domain.entities.UserAccount
import com.davidluna.architectcoders2024.core_domain.entities.errors.AppError

interface SessionDataSource {
    suspend fun createRequestToken(): Either<AppError, TokenResponse>
    suspend fun createSessionId(loginRequest: LoginRequest): Either<AppError, SessionId>
    suspend fun getUserAccount(): Either<AppError, UserAccount>
    suspend fun createGuestSession(): Either<AppError, GuestSession>
}