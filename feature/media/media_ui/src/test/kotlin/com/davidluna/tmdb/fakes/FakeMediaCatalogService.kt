package com.davidluna.tmdb.fakes

import arrow.core.Either
import arrow.core.right
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.media_data.framework.remote.datasources.MediaCatalogRemoteDatasource
import com.davidluna.tmdb.media_data.framework.remote.model.toDomain
import com.davidluna.tmdb.media_domain.entities.Media
import com.davidluna.tmdb.media_domain.entities.Results

class FakeMediaCatalogService : MediaCatalogRemoteDatasource {

    override suspend fun getMediaCatalog(
        endpoint: String,
        page: Int
    ): Either<AppError, Results<Media>> =
        if (endpoint.contains("movie")) {
            fakeRemoteMedia.toDomain().right()
        } else {
            fakeRemoteTvShows.toDomain().right()
        }
}