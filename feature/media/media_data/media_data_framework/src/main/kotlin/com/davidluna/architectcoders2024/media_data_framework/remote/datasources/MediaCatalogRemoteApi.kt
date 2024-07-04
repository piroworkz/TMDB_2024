package com.davidluna.architectcoders2024.media_data_framework.remote.datasources

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import com.davidluna.architectcoders2024.core_domain.core_entities.toAppError
import com.davidluna.architectcoders2024.media_data_framework.remote.services.MediaCatalogService
import com.davidluna.architectcoders2024.media_data_repositories.MediaCatalogRemoteDatasource
import com.davidluna.media_domain.media_domain_entities.Media
import com.davidluna.media_domain.media_domain_entities.Results
import javax.inject.Inject

class MediaCatalogRemoteApi @Inject constructor(private val service: MediaCatalogService) :
    MediaCatalogRemoteDatasource {

    override suspend fun getContent(
        endpoint: String,
        page: Int
    ): Either<AppError, Results<Media>> =
        service.getMedia(endpoint, page).fold(
            ifLeft = { it.toAppError().left() },
            ifRight = { it.toDomain().right() }
        )
}