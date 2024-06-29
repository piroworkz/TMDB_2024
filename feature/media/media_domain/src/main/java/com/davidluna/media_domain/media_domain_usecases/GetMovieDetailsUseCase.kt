package com.davidluna.media_domain.media_domain_usecases

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import com.davidluna.architectcoders2024.videos_domain.videos_domain_usecases.GetVideosUseCase
import com.davidluna.media_domain.media_domain_entities.Details
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieDetailsRepository,
    private val formatDate: FormatDateUseCase,
    private val getVideosUseCase: GetVideosUseCase
) {
    suspend operator fun invoke(endpoint: String): Either<AppError, Details> =
        repository.getMovieDetail(endpoint).fold(
            ifLeft = { it.left() },
            ifRight = {
                it.copy(
                    releaseDate = formatDate(releaseDate = it.releaseDate),
                    hasVideo = hasVideos(endpoint)
                ).right()
            }
        )


    private suspend fun hasVideos(endpoint: String): Boolean {
        return getVideosUseCase(endpoint).fold(
            ifLeft = { false },
            ifRight = { it.isNotEmpty() }
        )
    }
}
