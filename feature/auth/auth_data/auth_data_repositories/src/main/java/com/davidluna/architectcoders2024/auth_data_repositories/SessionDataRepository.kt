package com.davidluna.architectcoders2024.auth_data_repositories

import arrow.core.Either
import com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session.GuestSession
import com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session.LoginRequest
import com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session.SessionId
import com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session.TokenResponse
import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.SessionRepository
import com.davidluna.architectcoders2024.core_data_repositories.datastore.PreferencesDataSource
import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import com.davidluna.architectcoders2024.core_domain.core_entities.session.UserAccount
import javax.inject.Inject

class SessionDataRepository @Inject constructor(
    private val remote: SessionDatasource,
    private val local: PreferencesDataSource
) : SessionRepository {

    override suspend fun createRequestToken(): Either<AppError, TokenResponse> =
        remote.createRequestToken()

    override suspend fun createSessionId(loginRequest: LoginRequest): Either<AppError, SessionId> =
        remote.createSessionId(loginRequest).fold(
            ifLeft = { Either.Left(it) },
            ifRight = {
                local.saveSessionId(it.sessionId)
                Either.Right(it)
            }
        )

    override suspend fun getUserAccount(): Either<AppError, UserAccount> = remote.getAccount().fold(
        ifLeft = { Either.Left(it) },
        ifRight = {
            local.saveUser(it)
            Either.Right(it)
        }
    )

    override suspend fun createGuestSessionId(): Either<AppError, GuestSession> =
        remote.createGuestSessionId().fold(
            ifLeft = { Either.Left(it) },
            ifRight = {
                local.saveIsGuest(it.guestSessionId.isNotEmpty())
                local.saveSessionId(it.guestSessionId)
                Either.Right(it)
            }
        )
}
