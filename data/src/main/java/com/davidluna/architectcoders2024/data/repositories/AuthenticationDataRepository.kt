package com.davidluna.architectcoders2024.data.repositories

import arrow.core.Either
import com.davidluna.architectcoders2024.data.sources.AuthenticationDatasource
import com.davidluna.architectcoders2024.data.sources.LocalSessionDataSource
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.domain.requests.LoginRequest
import com.davidluna.architectcoders2024.domain.session.SessionId
import com.davidluna.architectcoders2024.domain.session.TokenResponse
import com.davidluna.architectcoders2024.domain.session.UserAccount
import com.davidluna.architectcoders2024.usecases.repositories.AuthenticationRepository
import javax.inject.Inject

class AuthenticationDataRepository @Inject constructor(
    private val remote: AuthenticationDatasource,
) : AuthenticationRepository {

    override suspend fun createRequestToken(): Either<AppError, TokenResponse> =
        remote.createRequestToken()

    override suspend fun createSessionId(loginRequest: LoginRequest): Either<AppError, SessionId> =
        remote.createSessionId(loginRequest)

    override suspend fun getUserAccount(): Either<AppError, UserAccount> = remote.getAccount()
}

