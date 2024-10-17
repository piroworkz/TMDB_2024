package com.davidluna.tmdb.auth_data.repositories

import arrow.core.Either
import com.davidluna.tmdb.auth_data.framework.remote.datasources.SessionDatasource
import com.davidluna.tmdb.auth_domain.entities.session.LoginRequest
import com.davidluna.tmdb.auth_domain.entities.session.TokenResponse
import com.davidluna.tmdb.auth_domain.repositories.SessionRepository
import com.davidluna.tmdb.core_data.framework.local.proto_datastore.PreferencesDataSource
import com.davidluna.tmdb.core_domain.entities.GuestSession
import com.davidluna.tmdb.core_domain.entities.Session
import com.davidluna.tmdb.core_domain.entities.SessionId
import com.davidluna.tmdb.core_domain.entities.UserAccount
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.core_domain.entities.errors.DataStoreErrorMessage.SAVE_GUEST_SESSION_ID
import com.davidluna.tmdb.core_domain.entities.errors.DataStoreErrorMessage.SAVE_SESSION_ID
import com.davidluna.tmdb.core_domain.entities.errors.DataStoreErrorMessage.SAVE_USER_ACCOUNT
import com.davidluna.tmdb.core_domain.entities.errors.buildAppError

class SessionDataRepository (
    private val remote: SessionDatasource,
    private val local: PreferencesDataSource
): SessionRepository {

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
        remote.createGuestSession().fold(
            ifLeft = { Either.Left(it) },
            ifRight = {
                if (local.saveSession(it.toSession())) {
                    Either.Right(it.toSession())
                } else {
                    Either.Left(SAVE_GUEST_SESSION_ID.buildAppError())
                }
            }
        )

    private fun SessionId.toSession(): Session {
        return Session(
            id = sessionId,
            guestSession = GuestSession(
                id = "",
                expiresAt = ""
            )
        )
    }

    private fun GuestSession.toSession(): Session {
        return Session(
            id = "",
            guestSession = GuestSession(
                id = id,
                expiresAt = expiresAt
            )
        )
    }
}