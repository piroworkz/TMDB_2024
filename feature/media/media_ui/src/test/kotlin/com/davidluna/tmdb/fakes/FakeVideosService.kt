package com.davidluna.tmdb.fakes

import arrow.core.Either
import arrow.core.right
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.media_data.framework.remote.datasources.VideosRemoteDataSource
import com.davidluna.tmdb.media_data.framework.remote.datasources.toDomain
import com.davidluna.tmdb.media_data.framework.remote.model.RemoteVideo
import com.davidluna.tmdb.media_domain.entities.Video

class FakeVideosService : VideosRemoteDataSource {

    override suspend fun getVideos(endpoint: String): Either<AppError, List<Video>> =
        if (endpoint.contains("movie")) {
            fakeRemoteMovieVideos.results.map(RemoteVideo::toDomain).right()
        } else {
            fakeRemoteTvShowVideos.results.map(RemoteVideo::toDomain).right()
        }
}