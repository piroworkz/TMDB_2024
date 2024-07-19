package com.davidluna.architectcoders2024.auth_data_repositories

import arrow.core.Either
import com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session.GuestSession
import com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session.LoginRequest
import com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session.TokenResponse
import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.SessionRepository
import com.davidluna.architectcoders2024.core_data_repositories.datastore.PreferencesDataSource
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

    override suspend fun createSessionId(loginRequest: LoginRequest): Either<AppError, SessionId> =
        remote.createSessionId(loginRequest).fold(
            ifLeft = { Either.Left(it) },
            ifRight = {
                if (local.saveSessionId(it.sessionId)) {
                    Either.Right(it)
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

    override suspend fun createGuestSessionId(): Either<AppError, GuestSession> =
        remote.createGuestSessionId().fold(
            ifLeft = { Either.Left(it) },
            ifRight = {
                if (local.saveIsGuest(true) && local.saveSessionId(it.guestSessionId)) {
                    Either.Right(it)
                } else {
                    Either.Left(SAVE_GUEST_SESSION_ID.buildAppError())
                }
            }
        )
}
