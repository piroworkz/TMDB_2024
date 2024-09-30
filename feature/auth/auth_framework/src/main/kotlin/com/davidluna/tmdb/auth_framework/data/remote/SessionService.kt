package com.davidluna.tmdb.auth_framework.data.remote

import arrow.core.Either
import com.davidluna.tmdb.auth_framework.data.remote.model.RemoteGuestSession
import com.davidluna.tmdb.auth_framework.data.remote.model.RemoteLoginRequest
import com.davidluna.tmdb.auth_framework.data.remote.model.RemoteSessionIdResponse
import com.davidluna.tmdb.auth_framework.data.remote.model.RemoteTokenResponse
import com.davidluna.tmdb.auth_framework.data.remote.model.RemoteUserAccountDetail
import com.davidluna.tmdb.core_framework.data.remote.model.RemoteError
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SessionService {
    @GET("authentication/token/new")
    suspend fun createRequestToken(): Either<RemoteError, RemoteTokenResponse>

    @POST("authentication/session/new")
    suspend fun createSessionId(@Body loginRequest: RemoteLoginRequest): Either<RemoteError, RemoteSessionIdResponse>

    @GET("account")
    suspend fun getAccount(): Either<RemoteError, RemoteUserAccountDetail>

    @GET("authentication/guest_session/new")
    suspend fun createGuestSessionId(): Either<RemoteError, RemoteGuestSession>
}
