package com.davidluna.architectcoders2024.media_data_repositories

import arrow.core.Either
import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import com.davidluna.media_domain.media_domain_entities.Media
import com.davidluna.media_domain.media_domain_entities.Results
import com.davidluna.media_domain.media_domain_usecases.MoviesRepository
import javax.inject.Inject

class MediaCatalogDataRepository @Inject constructor(
    private val remote: MediaCatalogRemoteDatasource
) : MoviesRepository {

    override suspend fun getContent(
        endpoint: String,
        page: Int
    ): Either<AppError, Results<Media>> =
        remote.getContent(endpoint, page)

}