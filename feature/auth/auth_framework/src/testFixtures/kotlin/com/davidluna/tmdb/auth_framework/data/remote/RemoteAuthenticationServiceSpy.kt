package com.davidluna.tmdb.auth_framework.data.remote

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.davidluna.tmdb.auth_framework.data.remote.model.RemoteGuestSession
import com.davidluna.tmdb.auth_framework.data.remote.model.RemoteLoginRequest
import com.davidluna.tmdb.auth_framework.data.remote.model.RemoteSessionIdResponse
import com.davidluna.tmdb.auth_framework.data.remote.model.RemoteTokenResponse
import com.davidluna.tmdb.auth_framework.data.remote.model.RemoteValidateTokenWithLoginRequest
import com.davidluna.tmdb.core_framework.data.remote.model.RemoteError
import com.davidluna.tmdb.test_shared.reader.Reader

class RemoteAuthenticationServiceSpy : RemoteAuthenticationService {

    private var shouldThrowError: Boolean = false

    fun throwError(shouldThrow: Boolean) {
        shouldThrowError = shouldThrow
    }

    override suspend fun createRequestToken(): Either<RemoteError, RemoteTokenResponse> =
        if (shouldThrowError) {
            Reader.fromJson<RemoteError>(Reader.REMOTE_ERROR).left()
        } else {
            Reader.fromJson<RemoteTokenResponse>(Reader.AUTH_TOKEN_NEW).right()
        }

    override suspend fun authorizeToken(loginRequest: RemoteValidateTokenWithLoginRequest): Either<RemoteError, RemoteTokenResponse> =
        if (shouldThrowError) {
            Reader.fromJson<RemoteError>(Reader.REMOTE_ERROR).left()
        } else {
            Reader.fromJson<RemoteTokenResponse>(Reader.AUTH_TOKEN_NEW).right()
        }

    override suspend fun createSessionId(loginRequest: RemoteLoginRequest): Either<RemoteError, RemoteSessionIdResponse> =
        if (shouldThrowError) {
            Reader.fromJson<RemoteError>(Reader.REMOTE_ERROR).left()
        } else {
            Reader.fromJson<RemoteSessionIdResponse>(Reader.AUTH_SESSION_NEW).right()
        }

    override suspend fun createGuestSession(): Either<RemoteError, RemoteGuestSession> =
        if (shouldThrowError) {
            Reader.fromJson<RemoteError>(Reader.REMOTE_ERROR).left()
        } else {
            Reader.fromJson<RemoteGuestSession>(Reader.AUTH_GUEST_SESSION).right()
        }
}