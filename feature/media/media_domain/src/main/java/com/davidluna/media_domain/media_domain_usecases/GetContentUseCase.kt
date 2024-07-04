package com.davidluna.media_domain.media_domain_usecases

import arrow.core.Either
import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import com.davidluna.media_domain.media_domain_entities.Media
import com.davidluna.media_domain.media_domain_entities.Results
import javax.inject.Inject

class GetContentUseCase @Inject constructor(private val repository: MoviesRepository) {
    suspend operator fun invoke(
        endpoint: String,
        page: Int
    ): Either<AppError, Results<Media>> =
        repository.getContent(endpoint, page)
}
