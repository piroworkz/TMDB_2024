package com.davidluna.architectcoders2024.test_shared_framework.integration.services

import arrow.core.Either
import arrow.core.right
import com.davidluna.architectcoders2024.auth_data_framework.remote.SessionService
import com.davidluna.architectcoders2024.auth_data_framework.remote.model.authentication.RemoteGuestSession
import com.davidluna.architectcoders2024.auth_data_framework.remote.model.authentication.RemoteLoginRequest
import com.davidluna.architectcoders2024.auth_data_framework.remote.model.authentication.RemoteSessionIdResponse
import com.davidluna.architectcoders2024.auth_data_framework.remote.model.authentication.RemoteTokenResponse
import com.davidluna.architectcoders2024.auth_data_framework.remote.model.authentication.RemoteUserAccountDetail
import com.davidluna.architectcoders2024.core_data_framework.remote.model.RemoteError
import com.davidluna.architectcoders2024.test_shared_framework.remote.fakeRemoteGuestSession
import com.davidluna.architectcoders2024.test_shared_framework.remote.fakeRemoteSessionId
import com.davidluna.architectcoders2024.test_shared_framework.remote.fakeRemoteTokenResponse
import com.davidluna.architectcoders2024.test_shared_framework.remote.fakeRemoteUserAccount

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