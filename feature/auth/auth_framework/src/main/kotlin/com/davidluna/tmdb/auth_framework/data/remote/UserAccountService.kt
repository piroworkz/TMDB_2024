package com.davidluna.tmdb.auth_framework.data.remote

import arrow.core.Either
import com.davidluna.tmdb.auth_framework.data.remote.model.RemoteUserAccountDetail
import com.davidluna.tmdb.core_framework.data.remote.model.RemoteError
import retrofit2.http.GET

interface UserAccountService {
    @GET("account")
    suspend fun getAccount(): Either<RemoteError, RemoteUserAccountDetail>
}