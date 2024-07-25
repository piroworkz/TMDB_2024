package com.davidluna.architectcoders2024.media_data_framework.remote.services

import arrow.core.Either
import arrow.core.right
import com.davidluna.architectcoders2024.framework.remote.model.RemoteError
import com.davidluna.architectcoders2024.framework.remote.media.RemoteMedia
import com.davidluna.architectcoders2024.framework.remote.media.RemoteResults
import com.davidluna.architectcoders2024.framework.remote.services.MediaCatalogService
import com.davidluna.architectcoders2024.test_shared_framework.remote.fakeRemoteMedia
import com.davidluna.architectcoders2024.test_shared_framework.remote.fakeRemoteTvShows

class FakeMediaCatalogServiceImpl : MediaCatalogService {
    override suspend fun getMediaCatalog(
        endpoint: String,
        page: Int
    ): Either<RemoteError, RemoteResults<RemoteMedia>> =
        if (endpoint.contains("movie")) {
            fakeRemoteMedia.right()
        } else {
            fakeRemoteTvShows.right()
        }
}