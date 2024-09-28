package com.davidluna.tmdb.fakes

import arrow.core.Either
import arrow.core.right
import com.davidluna.tmdb.media_framework.data.remote.media.RemoteMedia
import com.davidluna.tmdb.media_framework.data.remote.media.RemoteResults
import com.davidluna.tmdb.core_framework.data.remote.model.RemoteError
import com.davidluna.tmdb.media_framework.data.remote.services.MediaCatalogService

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