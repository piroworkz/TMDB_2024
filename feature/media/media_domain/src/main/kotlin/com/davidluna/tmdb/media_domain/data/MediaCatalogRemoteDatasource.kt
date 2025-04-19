package com.davidluna.tmdb.media_domain.data

import arrow.core.Either
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.media_domain.entities.Media
import com.davidluna.tmdb.media_domain.entities.Results

interface MediaCatalogRemoteDatasource {
   suspend fun getMediaCatalog(endpoint: String, page: Int): Either<AppError, Results<Media>>
}