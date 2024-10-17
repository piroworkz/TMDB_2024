package com.davidluna.tmdb.media_data.framework.remote.datasources

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.davidluna.tmdb.core_data.framework.remote.client.get
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.media_data.framework.remote.model.RemoteMedia
import com.davidluna.tmdb.media_data.framework.remote.model.RemoteResults
import com.davidluna.tmdb.media_data.framework.remote.model.toDomain
import com.davidluna.tmdb.media_domain.entities.Media
import com.davidluna.tmdb.media_domain.entities.Results
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter

class MediaCatalogService(private val client: HttpClient) : MediaCatalogRemoteDatasource {

    override suspend fun getMediaCatalog(endpoint: String, page: Int): Either<AppError, Results<Media>> =
        client.get<RemoteResults<RemoteMedia>>(
            urlString = endpoint,
            requestBuilder = { parameter(PAGE_NAME, page) }
        ).fold(
            ifLeft = { it.left() },
            ifRight = { it.toDomain().right() }
        )

    companion object {
        private const val PAGE_NAME = "page"
    }
}