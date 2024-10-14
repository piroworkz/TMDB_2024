package com.davidluna.tmdb.media_framework.data.remote.datasources

import arrow.core.Either
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.core_domain.entities.tryCatch
import com.davidluna.tmdb.media_domain.data.MediaCatalogRemoteDatasource
import com.davidluna.tmdb.media_domain.entities.Media
import com.davidluna.tmdb.media_domain.entities.Results
import com.davidluna.tmdb.media_framework.data.remote.media.RemoteMedia
import com.davidluna.tmdb.media_framework.data.remote.media.RemoteResults
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class MediaCatalogRemoteApi(private val client: HttpClient) : MediaCatalogRemoteDatasource {

    override suspend fun getMediaCatalog(
        endpoint: String,
        page: Int,
    ): Either<AppError, Results<Media>> =
        tryCatch(client.get(endpoint) { parameter(PAGE_NAME, page) }
            .body<RemoteResults<RemoteMedia>>()::toDomain)

    companion object {
        private const val PAGE_NAME = "page"
    }
}