package com.davidluna.architectcoders2024.fakes

import arrow.core.Either
import arrow.core.right
import com.davidluna.architectcoders2024.auth_framework.data.remote.SessionService
import com.davidluna.architectcoders2024.core_framework.data.remote.model.RemoteError
import com.davidluna.architectcoders2024.auth_framework.data.remote.model.RemoteGuestSession
import com.davidluna.architectcoders2024.auth_framework.data.remote.model.RemoteLoginRequest
import com.davidluna.architectcoders2024.auth_framework.data.remote.model.RemoteSessionIdResponse
import com.davidluna.architectcoders2024.auth_framework.data.remote.model.RemoteTokenResponse
import com.davidluna.architectcoders2024.auth_framework.data.remote.model.RemoteUserAccountDetail

class FakeSessionServiceImpl : SessionService {

    override suspend fun createRequestToken(): Either<RemoteError, RemoteTokenResponse> =
        fakeRemoteTokenResponse.right()

    override suspend fun createSessionId(loginRequest: RemoteLoginRequest): Either<RemoteError, RemoteSessionIdResponse> =
        fakeRemoteSessionId.right()

    override suspend fun getAccount(): Either<RemoteError, RemoteUserAccountDetail> =
        fakeRemoteUserAccount.right()

    override suspend fun createGuestSessionId(): Either<RemoteError, RemoteGuestSession> =
        fakeRemoteGuestSession.right()
}