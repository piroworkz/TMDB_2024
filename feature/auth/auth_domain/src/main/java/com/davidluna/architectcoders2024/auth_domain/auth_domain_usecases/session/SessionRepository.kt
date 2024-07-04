package com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session

import arrow.core.Either
import com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session.GuestSession
import com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session.LoginRequest
import com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session.SessionId
import com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session.TokenResponse
import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import com.davidluna.architectcoders2024.core_domain.core_entities.UserAccount

interface SessionRepository {
    suspend fun createRequestToken(): Either<AppError, TokenResponse>

    suspend fun createSessionId(loginRequest: LoginRequest): Either<AppError, SessionId>

    suspend fun getUserAccount(): Either<AppError, UserAccount>

    suspend fun createGuestSessionId(): Either<AppError, GuestSession>
}