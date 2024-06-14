package com.davidluna.architectcoders2024.app.data.remote.datasources

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.davidluna.architectcoders2024.app.data.remote.services.session.SessionService
import com.davidluna.architectcoders2024.app.utils.toAppError
import com.davidluna.architectcoders2024.data.sources.session.SessionDatasource
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.domain.requests.LoginRequest
import com.davidluna.architectcoders2024.domain.session.GuestSession
import com.davidluna.architectcoders2024.domain.session.SessionId
import com.davidluna.architectcoders2024.domain.session.TokenResponse
import com.davidluna.architectcoders2024.domain.session.UserAccount
import javax.inject.Inject

class ApiSessionDatasource @Inject constructor(
    private val service: SessionService
) : SessionDatasource {

    override suspend fun createRequestToken(): Either<AppError, TokenResponse> =
        service.createRequestToken().fold(
            ifLeft = { it.toAppError().left() },
            ifRight = { it.toDomain().right() }
        )

    override suspend fun createSessionId(loginRequest: LoginRequest): Either<AppError, SessionId> =
        service.createSessionId(loginRequest.toRemote()).fold(
            ifLeft = { it.toAppError().left() },
            ifRight = { it.toDomain().right() }
        )

    override suspend fun getAccount(): Either<AppError, UserAccount> =
        service.getAccount().fold(
            ifLeft = { it.toAppError().left() },
            ifRight = { it.toDomain().right() }
        )

    override suspend fun createGuestSessionId(): Either<AppError, GuestSession> =
        service.createGuestSessionId().fold(
            ifLeft = { it.toAppError().left() },
            ifRight = { it.toDomain().right() }
        )
}
