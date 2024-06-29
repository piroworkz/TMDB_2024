package com.davidluna.architectcoders2024.auth_data_framework.remote

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.davidluna.architectcoders2024.auth_data_framework.remote.model.authentication.toDomain
import com.davidluna.architectcoders2024.auth_data_framework.remote.model.authentication.toRemote
import com.davidluna.architectcoders2024.auth_data_repositories.SessionDatasource
import com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session.GuestSession
import com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session.LoginRequest
import com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session.SessionId
import com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session.TokenResponse
import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import com.davidluna.architectcoders2024.core_domain.core_entities.UserAccount
import com.davidluna.architectcoders2024.core_domain.core_entities.toAppError
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
