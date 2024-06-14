package com.davidluna.architectcoders2024.data.repositories.session

import arrow.core.Either
import com.davidluna.architectcoders2024.data.sources.session.SessionDatasource
import com.davidluna.architectcoders2024.data.sources.preferences.PreferencesDataSource
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.domain.requests.LoginRequest
import com.davidluna.architectcoders2024.domain.session.GuestSession
import com.davidluna.architectcoders2024.domain.session.SessionId
import com.davidluna.architectcoders2024.domain.session.TokenResponse
import com.davidluna.architectcoders2024.domain.session.UserAccount
import com.davidluna.architectcoders2024.usecases.repositories.SessionRepository
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
