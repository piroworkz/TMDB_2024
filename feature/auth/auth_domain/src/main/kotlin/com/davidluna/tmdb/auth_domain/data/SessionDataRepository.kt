package com.davidluna.tmdb.auth_domain.data

import arrow.core.Either
import com.davidluna.tmdb.auth_domain.entities.session.LoginRequest
import com.davidluna.tmdb.auth_domain.entities.session.TokenResponse
import com.davidluna.tmdb.auth_domain.usecases.SessionRepository
import com.davidluna.tmdb.core_domain.data.datastore.PreferencesDataSource
import com.davidluna.tmdb.core_domain.entities.GuestSession
import com.davidluna.tmdb.core_domain.entities.Session
import com.davidluna.tmdb.core_domain.entities.SessionId
import com.davidluna.tmdb.core_domain.entities.UserAccount
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.core_domain.entities.errors.DataStoreErrorMessage
import com.davidluna.tmdb.core_domain.entities.errors.buildAppError
import javax.inject.Inject

class SessionDataRepository @Inject constructor(
    private val remote: SessionDataSource,
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
                    Either.Left(DataStoreErrorMessage.SAVE_SESSION_ID.buildAppError())
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
                    Either.Left(DataStoreErrorMessage.SAVE_USER_ACCOUNT.buildAppError())
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
                    Either.Left(DataStoreErrorMessage.SAVE_GUEST_SESSION_ID.buildAppError())
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