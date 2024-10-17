package com.davidluna.tmdb.fakes

import arrow.core.Either
import arrow.core.right
import com.davidluna.tmdb.core_data.framework.remote.model.RemoteError
import com.davidluna.tmdb.auth_data.framework.remote.model.RemoteGuestSession
import com.davidluna.tmdb.auth_data.framework.remote.model.RemoteLoginRequest
import com.davidluna.tmdb.auth_data.framework.remote.model.RemoteSessionIdResponse
import com.davidluna.tmdb.auth_data.framework.remote.model.RemoteTokenResponse
import com.davidluna.tmdb.auth_data.framework.remote.model.RemoteUserAccountDetail

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