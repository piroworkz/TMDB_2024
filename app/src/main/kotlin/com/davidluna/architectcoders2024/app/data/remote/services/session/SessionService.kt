package com.davidluna.architectcoders2024.app.data.remote.services.session

import arrow.core.Either
import com.davidluna.architectcoders2024.app.data.remote.model.RemoteError
import com.davidluna.architectcoders2024.app.data.remote.model.authentication.RemoteGuestSession
import com.davidluna.architectcoders2024.app.data.remote.model.authentication.RemoteLoginRequest
import com.davidluna.architectcoders2024.app.data.remote.model.authentication.RemoteSessionIdResponse
import com.davidluna.architectcoders2024.app.data.remote.model.authentication.RemoteTokenResponse
import com.davidluna.architectcoders2024.app.data.remote.model.authentication.RemoteUserAccountDetail
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

