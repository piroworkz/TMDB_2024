package com.davidluna.tmdb.auth_framework.data.remote

import arrow.core.Either
import com.davidluna.tmdb.auth_domain.data.SessionDataSource
import com.davidluna.tmdb.auth_domain.entities.session.LoginRequest
import com.davidluna.tmdb.auth_domain.entities.session.TokenResponse
import com.davidluna.tmdb.auth_framework.data.remote.model.RemoteGuestSession
import com.davidluna.tmdb.auth_framework.data.remote.model.RemoteSessionIdResponse
import com.davidluna.tmdb.auth_framework.data.remote.model.RemoteTokenResponse
import com.davidluna.tmdb.auth_framework.data.remote.model.RemoteUserAccountDetail
import com.davidluna.tmdb.auth_framework.data.remote.model.toDomain
import com.davidluna.tmdb.auth_framework.data.remote.model.toRemote
import com.davidluna.tmdb.core_domain.entities.GuestSession
import com.davidluna.tmdb.core_domain.entities.SessionId
import com.davidluna.tmdb.core_domain.entities.UserAccount
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.core_domain.entities.tryCatch
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class RemoteSessionDataSource(private val client: HttpClient) : SessionDataSource {

    override suspend fun createRequestToken(): Either<AppError, TokenResponse> =
        tryCatch(client.get("authentication/token/new").body<RemoteTokenResponse>()::toDomain)

    override suspend fun createSessionId(loginRequest: LoginRequest): Either<AppError, SessionId> =
        tryCatch(client.post("authentication/session/new") { setBody(loginRequest.toRemote()) }.body<RemoteSessionIdResponse>()::toDomain)

    override suspend fun getUserAccount(): Either<AppError, UserAccount> =
        tryCatch(client.get("account").body<RemoteUserAccountDetail>()::toDomain)

    override suspend fun createGuestSession(): Either<AppError, GuestSession> =
        tryCatch(client.get("authentication/guest_session/new").body<RemoteGuestSession>()::toDomain)
}
