package com.davidluna.architectcoders2024.videos_domain.videos_domain_usecases

import arrow.core.Either
import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import com.davidluna.architectcoders2024.videos_domain.videos_domain_entities.YoutubeVideo
import javax.inject.Inject

class GetVideosUseCase @Inject constructor(private val repository: VideosRepository) {
    suspend operator fun invoke(endpoint: String): Either<AppError, List<YoutubeVideo>> =
        repository.getVideos(endpoint)
}