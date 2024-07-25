package com.davidluna.architectcoders2024.fakes

import arrow.core.Either
import arrow.core.right
import com.davidluna.architectcoders2024.framework.remote.model.RemoteError
import com.davidluna.architectcoders2024.framework.remote.model.RemoteVideos
import com.davidluna.architectcoders2024.framework.remote.service.VideosService

class FakeVideosServiceImpl : VideosService {

    override suspend fun getVideos(endpoint: String): Either<RemoteError, RemoteVideos> =
        if (endpoint.contains("movie")) {
            fakeRemoteMovieVideos.right()
        } else {
            fakeRemoteTvShowVideos.right()
        }
}