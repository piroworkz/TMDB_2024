package com.davidluna.architectcoders2024.videos_framework.data.remote.service

import arrow.core.Either
import com.davidluna.architectcoders2024.core_framework.data.remote.model.RemoteError
import com.davidluna.architectcoders2024.videos_framework.data.remote.model.RemoteVideos
import retrofit2.http.GET
import retrofit2.http.Path

interface VideosService {
    @GET("{endpoint}/videos")
    suspend fun getVideos(@Path("endpoint", encoded = true) endpoint: String): Either<RemoteError, RemoteVideos>
}