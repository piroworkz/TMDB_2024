package com.davidluna.architectcoders2024.usecases.repositories

import arrow.core.Either
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.domain.responses.movies.Results
import com.davidluna.architectcoders2024.domain.responses.movies.Content

interface TvShowRepository {
    suspend fun getTvShows(endpoint: String, page: Int): Either<AppError, Results<Content>>
}