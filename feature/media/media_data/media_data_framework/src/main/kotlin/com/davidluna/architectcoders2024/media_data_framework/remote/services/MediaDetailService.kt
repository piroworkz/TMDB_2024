package com.davidluna.architectcoders2024.media_data_framework.remote.services

import arrow.core.Either
import com.davidluna.architectcoders2024.core_data_framework.remote.model.RemoteError
import com.davidluna.architectcoders2024.media_data_framework.remote.media.RemoteContentDetail
import com.davidluna.architectcoders2024.media_data_framework.remote.media.RemoteCredits
import com.davidluna.architectcoders2024.media_data_framework.remote.media.RemoteImages
import retrofit2.http.GET
import retrofit2.http.Path

interface MediaDetailService {

    @GET("{endpoint}")
    suspend fun getDetailById(@Path("endpoint", encoded = true) endpoint: String): Either<RemoteError, RemoteContentDetail>

    @GET("{endpoint}/credits")
    suspend fun getCreditsById(@Path("endpoint", encoded = true) endpoint: String): Either<RemoteError, RemoteCredits>

    @GET("{endpoint}/images")
    suspend fun getImagesById(@Path("endpoint", encoded = true) endpoint: String): Either<RemoteError, RemoteImages>

}