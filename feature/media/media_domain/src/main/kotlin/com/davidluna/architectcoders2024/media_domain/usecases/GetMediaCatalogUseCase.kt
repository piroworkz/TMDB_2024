package com.davidluna.architectcoders2024.media_domain.usecases

import arrow.core.Either
import com.davidluna.architectcoders2024.core_domain.entities.errors.AppError
import com.davidluna.architectcoders2024.media_domain.entities.Media
import com.davidluna.architectcoders2024.media_domain.entities.Results
import javax.inject.Inject

class GetMediaCatalogUseCase @Inject constructor(private val repository: MediaRepository) {
    suspend operator fun invoke(
        endpoint: String,
        page: Int
    ): Either<AppError, Results<Media>> =
        repository.getMediaCatalog(endpoint, page)
}