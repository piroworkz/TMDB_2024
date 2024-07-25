package com.davidluna.architectcoders2024.framework.remote.services

import arrow.core.Either
import com.davidluna.architectcoders2024.framework.remote.model.RemoteError
import com.davidluna.architectcoders2024.framework.remote.media.RemoteCredits
import com.davidluna.architectcoders2024.framework.remote.media.RemoteImages
import com.davidluna.architectcoders2024.framework.remote.media.RemoteMediaDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface MediaDetailService {

    @GET("{endpoint}")
    suspend fun getDetailById(@Path("endpoint", encoded = true) endpoint: String): Either<RemoteError, RemoteMediaDetail>

    @GET("{endpoint}/credits")
    suspend fun getCreditsById(@Path("endpoint", encoded = true) endpoint: String): Either<RemoteError, RemoteCredits>

    @GET("{endpoint}/images")
    suspend fun getImagesById(@Path("endpoint", encoded = true) endpoint: String): Either<RemoteError, RemoteImages>

}