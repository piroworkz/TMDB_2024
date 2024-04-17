package com.davidluna.architectcoders2024.app.data.remote.services.authentication

import com.davidluna.architectcoders2024.app.data.remote.model.authentication.RemoteLoginRequest
import com.davidluna.architectcoders2024.app.data.remote.model.authentication.RemoteSessionIdResponse
import com.davidluna.architectcoders2024.app.data.remote.model.authentication.RemoteTokenResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthenticationService {
    @GET("authentication/token/new")
    suspend fun createRequestToken(): RemoteTokenResponse

    @POST("authentication/session/new")
    suspend fun createSessionId(@Body loginRequest: RemoteLoginRequest): RemoteSessionIdResponse

    @GET("account")
    suspend fun getAccount(): RemoteUserAccountDetail
}

data class RemoteUserAccountDetail(
    val avatar: Avatar,
    val id: Int,
    val include_adult: Boolean,
    val iso_3166_1: String,
    val iso_639_1: String,
    val name: String,
    val username: String
)

data class Avatar(
    val gravatar: Gravatar,
    val tmdb: Tmdb
)

data class Gravatar(
    val hash: String
)

data class Tmdb(
    val avatar_path: String
)