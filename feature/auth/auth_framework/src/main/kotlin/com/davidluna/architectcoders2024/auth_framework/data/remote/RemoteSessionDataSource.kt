package com.davidluna.tmdb.auth_framework.data.remote

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.davidluna.tmdb.auth_domain.data.SessionDataSource
import com.davidluna.tmdb.auth_domain.entities.session.LoginRequest
import com.davidluna.tmdb.auth_domain.entities.session.TokenResponse
import com.davidluna.tmdb.auth_framework.data.remote.model.toDomain
import com.davidluna.tmdb.auth_framework.data.remote.model.toRemote
import com.davidluna.tmdb.core_domain.entities.GuestSession
import com.davidluna.tmdb.core_domain.entities.SessionId
import com.davidluna.tmdb.core_domain.entities.UserAccount
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.core_domain.entities.errors.toAppError
import javax.inject.Inject

class RemoteSessionDataSource @Inject constructor(
    private val service: SessionService
) : SessionDataSource {

    override suspend fun createRequestToken(): Either<AppError, TokenResponse> =
        service.createRequestToken().fold(
            ifLeft = { it.toAppError().left() },
            ifRight = { it.toDomain().right() }
        )

    override suspend fun createSessionId(loginRequest: LoginRequest): Either<AppError, com.davidluna.tmdb.core_domain.entities.SessionId> =
        service.createSessionId(loginRequest.toRemote()).fold(
            ifLeft = { it.toAppError().left() },
            ifRight = { it.toDomain().right() }
        )

    override suspend fun getUserAccount(): Either<AppError, com.davidluna.tmdb.core_domain.entities.UserAccount> =
        service.getAccount().fold(
            ifLeft = { it.toAppError().left() },
            ifRight = { it.toDomain().right() }
        )

    override suspend fun createGuestSession(): Either<AppError, com.davidluna.tmdb.core_domain.entities.GuestSession> =
        service.createGuestSessionId().fold(
            ifLeft = { it.toAppError().left() },
            ifRight = { it.toDomain().right() }
        )
}
