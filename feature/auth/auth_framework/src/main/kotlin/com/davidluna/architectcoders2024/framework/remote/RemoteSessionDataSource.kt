package com.davidluna.architectcoders2024.framework.remote

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.davidluna.architectcoders2024.framework.remote.model.authentication.toDomain
import com.davidluna.architectcoders2024.framework.remote.model.authentication.toRemote
import com.davidluna.architectcoders2024.auth_domain.data.SessionDataSource
import com.davidluna.architectcoders2024.auth_domain.entities.session.LoginRequest
import com.davidluna.architectcoders2024.auth_domain.entities.session.TokenResponse
import com.davidluna.architectcoders2024.core_domain.entities.GuestSession
import com.davidluna.architectcoders2024.core_domain.entities.SessionId
import com.davidluna.architectcoders2024.core_domain.entities.UserAccount
import com.davidluna.architectcoders2024.core_domain.entities.errors.AppError
import com.davidluna.architectcoders2024.core_domain.entities.errors.toAppError
import javax.inject.Inject

class RemoteSessionDataSource @Inject constructor(
    private val service: SessionService
) : SessionDataSource {

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

    override suspend fun getUserAccount(): Either<AppError, UserAccount> =
        service.getAccount().fold(
            ifLeft = { it.toAppError().left() },
            ifRight = { it.toDomain().right() }
        )

    override suspend fun createGuestSession(): Either<AppError, GuestSession> =
        service.createGuestSessionId().fold(
            ifLeft = { it.toAppError().left() },
            ifRight = { it.toDomain().right() }
        )
}
