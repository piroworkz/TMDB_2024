package com.davidluna.architectcoders2024.framework.remote.service

import arrow.core.Either
import com.davidluna.architectcoders2024.framework.remote.model.RemoteError
import com.davidluna.architectcoders2024.framework.remote.model.RemoteVideos
import retrofit2.http.GET
import retrofit2.http.Path

interface VideosService {
    @GET("{endpoint}/videos")
    suspend fun getVideos(@Path("endpoint", encoded = true) endpoint: String): Either<RemoteError, RemoteVideos>
}