package com.davidluna.architectcoders2024.app.data.repositories

import arrow.core.Either
import com.davidluna.architectcoders2024.app.data.remote.model.RemoteError
import com.davidluna.architectcoders2024.app.data.remote.model.authentication.RemoteLoginRequest
import com.davidluna.architectcoders2024.app.data.remote.model.authentication.RemoteSessionIdResponse
import com.davidluna.architectcoders2024.app.data.remote.model.authentication.RemoteTokenResponse
import com.davidluna.architectcoders2024.app.data.remote.model.authentication.RemoteUserAccountDetail
import com.davidluna.architectcoders2024.app.data.remote.services.authentication.AuthenticationService

class AuthenticationRepository(private val remote: AuthenticationService) {

    suspend fun createRequestToken(): Either<RemoteError, RemoteTokenResponse> =
        remote.createRequestToken()

    suspend fun createSessionId(loginRequest: RemoteLoginRequest): Either<RemoteError, RemoteSessionIdResponse> =
        remote.createSessionId(loginRequest)

    suspend fun getAccount(): Either<RemoteError, RemoteUserAccountDetail> = remote.getAccount()
}