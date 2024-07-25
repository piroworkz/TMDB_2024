package com.davidluna.architectcoders2024.media_domain.data

import arrow.core.Either
import com.davidluna.architectcoders2024.core_domain.entities.errors.AppError
import com.davidluna.architectcoders2024.media_domain.entities.Media
import com.davidluna.architectcoders2024.media_domain.entities.Results
import com.davidluna.architectcoders2024.media_domain.usecases.MediaRepository
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