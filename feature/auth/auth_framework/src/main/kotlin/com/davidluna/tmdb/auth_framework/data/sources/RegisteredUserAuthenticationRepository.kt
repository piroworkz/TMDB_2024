package com.davidluna.tmdb.auth_framework.data.sources

import arrow.core.Either
import com.davidluna.tmdb.auth_domain.entities.LoginRequest
import com.davidluna.tmdb.auth_domain.usecases.FetchUserAccountUseCase
import com.davidluna.tmdb.auth_domain.usecases.LoginWithCredentials
import com.davidluna.tmdb.auth_framework.data.local.database.dao.SessionDao
import com.davidluna.tmdb.auth_framework.data.local.database.entities.RoomSession
import com.davidluna.tmdb.auth_framework.data.remote.RemoteAuthenticationService
import com.davidluna.tmdb.auth_framework.data.remote.model.RemoteLoginRequest
import com.davidluna.tmdb.auth_framework.data.remote.model.RemoteSessionIdResponse
import com.davidluna.tmdb.auth_framework.data.remote.model.RemoteTokenResponse
import com.davidluna.tmdb.auth_framework.data.remote.model.RemoteValidateTokenWithLoginRequest
import com.davidluna.tmdb.core_domain.entities.AppError
import com.davidluna.tmdb.core_domain.entities.toAppError
import com.davidluna.tmdb.core_domain.entities.tryCatch
import com.davidluna.tmdb.core_framework.data.remote.model.toAppError
import javax.inject.Inject

class RegisteredUserAuthenticationRepository @Inject constructor(
    private val remote: RemoteAuthenticationService,
    private val fetchUserAccountUseCase: FetchUserAccountUseCase,
    private val local: SessionDao
) : LoginWithCredentials {

    override suspend fun invoke(request: LoginRequest): Either<AppError, Unit> = tryCatch {
        val token: RemoteTokenResponse = createTokenRequest()
        val authorization: RemoteTokenResponse = authorizeToken(token, request)
        val session: RemoteSessionIdResponse = createSession(authorization)
        local.insertSession(session.toLocalStorage())
        fetchUserAccount()
    }

    private suspend fun createTokenRequest(): RemoteTokenResponse =
        remote.createRequestToken().fold(
            ifLeft = { throw it.toAppError() },
            ifRight = { it }
        )

    private suspend fun authorizeToken(
        token: RemoteTokenResponse,
        request: LoginRequest,
    ): RemoteTokenResponse =
        remote.authorizeToken(token.toLoginRequest(request)).fold(
            ifLeft = { throw it.toAppError() },
            ifRight = { it }
        )

    private suspend fun createSession(token: RemoteTokenResponse): RemoteSessionIdResponse =
        remote.createSessionId(RemoteLoginRequest(requestToken = token.requestToken)).fold(
            ifLeft = { throw it.toAppError() },
            ifRight = { it }
        )

    private suspend fun fetchUserAccount() {
        fetchUserAccountUseCase().fold(
            ifLeft = { throw it.toAppError() },
            ifRight = {}
        )
    }

    private fun RemoteTokenResponse.toLoginRequest(request: LoginRequest) =
        RemoteValidateTokenWithLoginRequest(
            requestToken = requestToken,
            username = request.username,
            password = request.password
        )

    private fun RemoteSessionIdResponse.toLocalStorage(): RoomSession = RoomSession(
        sessionId = sessionId,
        isGuest = false,
        expiresAt = null
    )
}
