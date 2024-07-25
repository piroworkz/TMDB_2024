package com.davidluna.architectcoders2024.fakes

import arrow.core.Either
import arrow.core.right
import com.davidluna.architectcoders2024.framework.remote.media.RemoteCredits
import com.davidluna.architectcoders2024.framework.remote.media.RemoteImages
import com.davidluna.architectcoders2024.framework.remote.media.RemoteMediaDetail
import com.davidluna.architectcoders2024.framework.remote.model.RemoteError
import com.davidluna.architectcoders2024.framework.remote.services.MediaDetailService

class FakeMediaDetailServiceImpl : MediaDetailService {
    override suspend fun getDetailById(endpoint: String): Either<RemoteError, RemoteMediaDetail> =
        if (endpoint.contains("movie")) {
            fakeRemoteMediaDetail.right()
        } else {
            fakeRemoteTvShowDetail.right()
        }

    override suspend fun getCreditsById(endpoint: String): Either<RemoteError, RemoteCredits> =
        if (endpoint.contains("movie")) {
            fakeRemoteCredits.right()
        } else {
            fakeRemoteTvShowCredits.right()
        }

    override suspend fun getImagesById(endpoint: String): Either<RemoteError, RemoteImages> =
        if (endpoint.contains("movie")) {
            fakeRemoteImages.right()
        } else {
            fakeRemoteTvShowImages.right()
        }
}