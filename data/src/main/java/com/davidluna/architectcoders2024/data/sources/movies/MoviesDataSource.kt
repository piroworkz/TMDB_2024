package com.davidluna.architectcoders2024.data.sources.movies

import arrow.core.Either
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.domain.responses.movies.Content
import com.davidluna.architectcoders2024.domain.responses.movies.Results

interface MoviesDataSource {
   suspend fun getContent(endpoint: String, page: Int): Either<AppError, Results<Content>>
}