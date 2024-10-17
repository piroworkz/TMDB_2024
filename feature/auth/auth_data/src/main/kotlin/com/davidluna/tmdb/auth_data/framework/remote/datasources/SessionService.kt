package com.davidluna.tmdb.auth_data.framework.remote.datasources

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.davidluna.tmdb.auth_data.framework.remote.model.RemoteGuestSession
import com.davidluna.tmdb.auth_data.framework.remote.model.RemoteSessionIdResponse
import com.davidluna.tmdb.auth_data.framework.remote.model.RemoteTokenResponse
import com.davidluna.tmdb.auth_data.framework.remote.model.RemoteUserAccountDetail
import com.davidluna.tmdb.auth_data.framework.remote.model.toDomain
import com.davidluna.tmdb.auth_data.framework.remote.model.toRemote
import com.davidluna.tmdb.auth_domain.entities.session.LoginRequest
import com.davidluna.tmdb.auth_domain.entities.session.TokenResponse
import com.davidluna.tmdb.core_data.framework.remote.client.get
import com.davidluna.tmdb.core_data.framework.remote.client.post
import com.davidluna.tmdb.core_domain.entities.GuestSession
import com.davidluna.tmdb.core_domain.entities.SessionId
import com.davidluna.tmdb.core_domain.entities.UserAccount
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import io.ktor.client.HttpClient
import io.ktor.client.request.setBody

class SessionService(private val client: HttpClient) : SessionDatasource {

    override suspend fun createRequestToken(): Either<AppError, TokenResponse> =
        client.get<RemoteTokenResponse>("authentication/token/new").fold(
            ifLeft = { it.left() },
            ifRight = { it.toDomain().right() }
        )

    override suspend fun createSessionId(loginRequest: LoginRequest): Either<AppError, SessionId> =
        client.post<RemoteSessionIdResponse>("authentication/session/new") { setBody(loginRequest.toRemote()) }
            .fold(
                ifLeft = { it.left() },
                ifRight = { it.toDomain().right() }
            )

    override suspend fun getUserAccount(): Either<AppError, UserAccount> =
        client.get<RemoteUserAccountDetail>("account").fold(
            ifLeft = { it.left() },
            ifRight = { it.toDomain().right() }
        )

    override suspend fun createGuestSession(): Either<AppError, GuestSession> =
        client.get<RemoteGuestSession>("authentication/guest_session/new").fold(
            ifLeft = { it.left() },
            ifRight = { it.toDomain().right() }
        )

}
