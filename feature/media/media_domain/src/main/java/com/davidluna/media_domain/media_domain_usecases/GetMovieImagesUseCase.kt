package com.davidluna.media_domain.media_domain_usecases

import arrow.core.Either
import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import com.davidluna.media_domain.media_domain_entities.Image
import javax.inject.Inject

class GetMovieImagesUseCase @Inject constructor(private val repository: MovieDetailsRepository) {
    suspend operator fun invoke(endpoint: String): Either<AppError, List<Image>> =
        repository.getMovieImages(endpoint)
}