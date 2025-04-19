package com.davidluna.tmdb.media_domain.usecases

import arrow.core.Either
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.media_domain.entities.Image
import javax.inject.Inject

class GetMediaImagesUseCase @Inject constructor(private val repository: MovieDetailsRepository) {
    suspend operator fun invoke(endpoint: String): Either<AppError, List<Image>> =
        repository.getMovieImages(endpoint)
}