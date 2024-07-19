package com.davidluna.architectcoders2024.media_data_repositories

import arrow.core.Either
import com.davidluna.architectcoders2024.core_domain.core_entities.errors.AppError
import com.davidluna.architectcoders2024.media_domain.media_domain_entities.Media
import com.davidluna.architectcoders2024.media_domain.media_domain_entities.Results
import com.davidluna.architectcoders2024.media_domain.media_domain_usecases.MediaRepository
import javax.inject.Inject

class MediaCatalogDataRepository @Inject constructor(
    private val remote: MediaCatalogRemoteDatasource
) : MediaRepository {

    override suspend fun getMediaCatalog(
        endpoint: String,
        page: Int
    ): Either<AppError, Results<Media>> =
        remote.getMediaCatalog(endpoint, page)

}