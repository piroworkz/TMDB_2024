package com.davidluna.architectcoders2024.app.data.remote.services.authentication

import com.davidluna.architectcoders2024.app.data.remote.model.authentication.RemoteRequestSessionId
import com.davidluna.architectcoders2024.app.data.remote.model.authentication.RemoteSessionIdResponse
import com.davidluna.architectcoders2024.app.data.remote.model.authentication.RemoteTokenResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthenticationService {
    @GET("authentication/token/new")
    suspend fun getAuthenticationToken(): RemoteTokenResponse

    @POST("authentication/session/new")
    suspend fun requestSessionId(@Body loginRequest: RemoteRequestSessionId): RemoteSessionIdResponse
}
