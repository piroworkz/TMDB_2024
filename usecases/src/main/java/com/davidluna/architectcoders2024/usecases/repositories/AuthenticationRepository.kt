package com.davidluna.architectcoders2024.usecases.repositories

import arrow.core.Either
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.domain.requests.LoginRequest
import com.davidluna.architectcoders2024.domain.session.SessionId
import com.davidluna.architectcoders2024.domain.session.TokenResponse
import com.davidluna.architectcoders2024.domain.session.UserAccount
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    suspend fun createRequestToken(): Either<AppError, TokenResponse>

    suspend fun createSessionId(loginRequest: LoginRequest): Either<AppError, SessionId>

    suspend fun getUserAccount(): Either<AppError, UserAccount>

}