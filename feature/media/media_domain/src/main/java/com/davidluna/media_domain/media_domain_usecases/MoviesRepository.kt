package com.davidluna.media_domain.media_domain_usecases

import arrow.core.Either
import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import com.davidluna.media_domain.media_domain_entities.Media
import com.davidluna.media_domain.media_domain_entities.Results

interface MoviesRepository {
    suspend fun getContent(endpoint: String, page: Int): Either<AppError, Results<Media>>
}