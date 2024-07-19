package com.davidluna.architectcoders2024.media_data_framework.remote.datasources

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.davidluna.architectcoders2024.core_domain.core_entities.errors.AppError
import com.davidluna.architectcoders2024.core_domain.core_entities.errors.toAppError
import com.davidluna.architectcoders2024.media_data_framework.remote.services.MediaCatalogService
import com.davidluna.architectcoders2024.media_data_repositories.MediaCatalogRemoteDatasource
import com.davidluna.architectcoders2024.media_domain.media_domain_entities.Media
import com.davidluna.architectcoders2024.media_domain.media_domain_entities.Results
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