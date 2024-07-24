package com.davidluna.architectcoders2024.auth_data_repositories

import arrow.core.Either
import com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session.LoginRequest
import com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session.TokenResponse
import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.SessionRepository
import com.davidluna.architectcoders2024.core_data_repositories.datastore.PreferencesDataSource
import com.davidluna.architectcoders2024.core_domain.core_entities.GuestSession
import com.davidluna.architectcoders2024.core_domain.core_entities.Session
import com.davidluna.architectcoders2024.core_domain.core_entities.SessionId
import com.davidluna.architectcoders2024.core_domain.core_entities.UserAccount
import com.davidluna.architectcoders2024.core_domain.core_entities.errors.AppError
import com.davidluna.architectcoders2024.core_domain.core_entities.errors.DataStoreErrorMessage.SAVE_GUEST_SESSION_ID
import com.davidluna.architectcoders2024.core_domain.core_entities.errors.DataStoreErrorMessage.SAVE_SESSION_ID
import com.davidluna.architectcoders2024.core_domain.core_entities.errors.DataStoreErrorMessage.SAVE_USER_ACCOUNT
import com.davidluna.architectcoders2024.core_domain.core_entities.errors.buildAppError
import javax.inject.Inject

class SessionDataRepository @Inject constructor(
    private val remote: SessionDatasource,
    private val local: PreferencesDataSource
) : SessionRepository {

    override suspend fun createRequestToken(): Either<AppError, TokenResponse> =
        remote.createRequestToken()

    override suspend fun createSessionId(loginRequest: LoginRequest): Either<AppError, Session> =
        remote.createSessionId(loginRequest).fold(
            ifLeft = { Either.Left(it) },
            ifRight = {
                if (local.saveSession(it.toSession())) {
                    Either.Right(it.toSession())
                } else {
                    Either.Left(SAVE_SESSION_ID.buildAppError())
                }
            }
        )

    override suspend fun getUserAccount(): Either<AppError, UserAccount> =
        remote.getUserAccount().fold(
            ifLeft = { Either.Left(it) },
            ifRight = {
                if (local.saveUser(it)) {
                    Either.Right(it)
                } else {
                    Either.Left(SAVE_USER_ACCOUNT.buildAppError())
                }
            }
        )

    override suspend fun createGuestSession(): Either<AppError, Session> =
        remote.createGuestSessionId().fold(
            ifLeft = { Either.Left(it) },
            ifRight = {
                if (local.saveSession(it.toSession())) {
                    Either.Right(it.toSession())
                } else {
                    Either.Left(SAVE_GUEST_SESSION_ID.buildAppError())
                }
            }
        )
}

private fun SessionId.toSession(): Session {
    return Session(
        id = sessionId,
        guestSession = GuestSession(
            id = "",
            expiresAt = "",
            isGuest = false
        )
    )
}

private fun GuestSession.toSession(): Session {
    return Session(
        id = "",
        guestSession = GuestSession(
            id = id,
            expiresAt = expiresAt,
            isGuest = true
        )
    )
}
