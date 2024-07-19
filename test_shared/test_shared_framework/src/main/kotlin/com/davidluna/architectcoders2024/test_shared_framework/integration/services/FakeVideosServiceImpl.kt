package com.davidluna.architectcoders2024.test_shared_framework.integration.services

import arrow.core.Either
import arrow.core.right
import com.davidluna.architectcoders2024.core_data_framework.remote.model.RemoteError
import com.davidluna.architectcoders2024.test_shared_framework.remote.fakeRemoteMovieVideos
import com.davidluna.architectcoders2024.test_shared_framework.remote.fakeRemoteTvShowVideos
import com.davidluna.architectcoders2024.videos_data_framework.remote.model.RemoteVideos
import com.davidluna.architectcoders2024.videos_data_framework.remote.service.VideosService

class FakeVideosServiceImpl : VideosService {

    override suspend fun getVideos(endpoint: String): Either<RemoteError, RemoteVideos> =
        if (endpoint.contains("movie")) {
            fakeRemoteMovieVideos.right()
        } else {
            fakeRemoteTvShowVideos.right()
        }
}