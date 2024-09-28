package com.davidluna.tmdb.media_domain.usecases

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.media_domain.entities.MediaDetails
import com.davidluna.tmdb.videos_domain.usecases.GetVideosUseCase
import javax.inject.Inject

class GetMediaDetailsUseCase @Inject constructor(
    private val repository: MovieDetailsRepository,
    private val formatDate: FormatDateUseCase,
    private val getVideosUseCase: GetVideosUseCase
) {
    suspend operator fun invoke(endpoint: String): Either<AppError, MediaDetails> =
        repository.getMovieDetail(endpoint).fold(
            ifLeft = { it.left() },
            ifRight = {
                it.copy(
                    releaseDate = formatDate(releaseDate = it.releaseDate) ?: "",
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
