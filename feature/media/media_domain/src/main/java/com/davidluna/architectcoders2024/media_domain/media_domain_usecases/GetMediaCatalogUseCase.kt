package com.davidluna.architectcoders2024.media_domain.media_domain_usecases

import arrow.core.Either
import com.davidluna.architectcoders2024.core_domain.core_entities.errors.AppError
import com.davidluna.architectcoders2024.media_domain.media_domain_entities.Media
import com.davidluna.architectcoders2024.media_domain.media_domain_entities.Results
import javax.inject.Inject

class GetMediaCatalogUseCase @Inject constructor(private val repository: MediaRepository) {
    suspend operator fun invoke(
        endpoint: String,
        page: Int
    ): Either<AppError, Results<Media>> =
        repository.getMediaCatalog(endpoint, page)
}
