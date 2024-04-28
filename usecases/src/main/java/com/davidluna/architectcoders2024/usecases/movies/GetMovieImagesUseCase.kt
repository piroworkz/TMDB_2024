package com.davidluna.architectcoders2024.usecases.movies

import arrow.core.Either
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.domain.responses.movies.Image
import com.davidluna.architectcoders2024.usecases.repositories.MovieDetailsRepository
import javax.inject.Inject

class GetMovieImagesUseCase @Inject constructor(private val repository: MovieDetailsRepository) {
    suspend operator fun invoke(movieId: Int): Either<AppError, List<Image>> =
        repository.getMovieImages(movieId)
}