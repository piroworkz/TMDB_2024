package com.davidluna.architectcoders2024.videos_data_framework.remote.service

import arrow.core.Either
import com.davidluna.architectcoders2024.core_data_framework.remote.model.RemoteError
import com.davidluna.architectcoders2024.videos_data_framework.remote.model.RemoteVideos
import retrofit2.http.GET
import retrofit2.http.Path

interface VideosService {
    @GET("{endpoint}/videos")
    suspend fun getVideos(@Path("endpoint") endpoint: String): Either<RemoteError, RemoteVideos>
}