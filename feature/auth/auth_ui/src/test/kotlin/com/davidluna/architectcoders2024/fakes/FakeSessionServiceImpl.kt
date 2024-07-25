package com.davidluna.architectcoders2024.fakes

import arrow.core.Either
import arrow.core.right
import com.davidluna.architectcoders2024.framework.remote.SessionService
import com.davidluna.architectcoders2024.framework.remote.model.RemoteError
import com.davidluna.architectcoders2024.framework.remote.model.authentication.RemoteGuestSession
import com.davidluna.architectcoders2024.framework.remote.model.authentication.RemoteLoginRequest
import com.davidluna.architectcoders2024.framework.remote.model.authentication.RemoteSessionIdResponse
import com.davidluna.architectcoders2024.framework.remote.model.authentication.RemoteTokenResponse
import com.davidluna.architectcoders2024.framework.remote.model.authentication.RemoteUserAccountDetail

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