package com.davidluna.architectcoders2024.app.data.remote

import com.davidluna.architectcoders2024.app.data.remote.model.authentication.RemoteLoginRequest
import com.davidluna.architectcoders2024.app.data.remote.model.authentication.RemoteSessionIdResponse
import com.davidluna.architectcoders2024.app.data.remote.model.authentication.RemoteTokenResponse
import com.davidluna.architectcoders2024.app.data.remote.services.authentication.AuthenticationService
import com.davidluna.architectcoders2024.app.data.remote.services.authentication.RemoteUserAccountDetail

class AuthenticationRepository {

    private val service: AuthenticationService = ApiClient.authenticationService
    private val sessionProvider: SessionProvider = MoviesInterceptor()

    suspend fun createRequestToken(): RemoteTokenResponse = service.createRequestToken()

    suspend fun createSessionId(loginRequest: RemoteLoginRequest): RemoteSessionIdResponse {
        val response = service.createSessionId(loginRequest)
        sessionProvider.setSessionId(response.sessionId)
        return response
    }

    suspend fun getAccount(): RemoteUserAccountDetail = service.getAccount()
}