package com.davidluna.architectcoders2024.media_framework.data.remote.datasources

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.davidluna.architectcoders2024.core_domain.entities.errors.AppError
import com.davidluna.architectcoders2024.core_domain.entities.errors.toAppError
import com.davidluna.architectcoders2024.media_domain.data.MediaCatalogRemoteDatasource
import com.davidluna.architectcoders2024.media_domain.entities.Media
import com.davidluna.architectcoders2024.media_domain.entities.Results
import com.davidluna.architectcoders2024.media_framework.data.remote.services.MediaCatalogService
import javax.inject.Inject

class MediaCatalogRemoteApi @Inject constructor(private val service: MediaCatalogService) :
    MediaCatalogRemoteDatasource {

    override suspend fun getMediaCatalog(
        endpoint: String,
        page: Int
    ): Either<AppError, Results<Media>> =
        service.getMediaCatalog(endpoint, page).fold(
            ifLeft = { it.toAppError().left() },
            ifRight = { it.toDomain().right() }
        )

}