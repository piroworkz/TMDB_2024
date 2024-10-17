package com.davidluna.tmdb.fakes

import arrow.core.Either
import arrow.core.right
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.media_data.framework.remote.datasources.MediaDetailsRemoteDatasource
import com.davidluna.tmdb.media_data.framework.remote.model.RemoteCast
import com.davidluna.tmdb.media_data.framework.remote.model.RemoteImage
import com.davidluna.tmdb.media_data.framework.remote.model.toDomain
import com.davidluna.tmdb.media_domain.entities.Cast
import com.davidluna.tmdb.media_domain.entities.Image
import com.davidluna.tmdb.media_domain.entities.MediaDetails

class FakeMediaDetailService : MediaDetailsRemoteDatasource {

    override suspend fun getMovieDetail(endpoint: String): Either<AppError, MediaDetails> =
        if (endpoint.contains("movie")) {
            fakeRemoteMediaDetail.toDomain().right()
        } else {
            fakeRemoteTvShowDetail.toDomain().right()
        }

    override suspend fun getMovieCast(endpoint: String): Either<AppError, List<Cast>> =
        if (endpoint.contains("movie")) {
            fakeRemoteCredits.cast.map(RemoteCast::toDomain).right()
        } else {
            fakeRemoteTvShowCredits.cast.map(RemoteCast::toDomain).right()
        }

    override suspend fun getMovieImages(endpoint: String): Either<AppError, List<Image>> =
        if (endpoint.contains("movie")) {
            fakeRemoteImages.posters.map(RemoteImage::toDomain).right()
        } else {
            fakeRemoteTvShowImages.posters.map(RemoteImage::toDomain).right()
        }

}