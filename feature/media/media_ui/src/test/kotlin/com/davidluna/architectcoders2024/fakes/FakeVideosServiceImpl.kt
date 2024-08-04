package com.davidluna.architectcoders2024.fakes

import arrow.core.Either
import arrow.core.right
import com.davidluna.architectcoders2024.core_framework.data.remote.model.RemoteError
import com.davidluna.architectcoders2024.videos_framework.data.remote.model.RemoteVideos
import com.davidluna.architectcoders2024.videos_framework.data.remote.service.VideosService

class FakeVideosServiceImpl : VideosService {

    override suspend fun getVideos(endpoint: String): Either<RemoteError, RemoteVideos> =
        if (endpoint.contains("movie")) {
            fakeRemoteMovieVideos.right()
        } else {
            fakeRemoteTvShowVideos.right()
        }
}