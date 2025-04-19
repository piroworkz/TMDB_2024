package com.davidluna.tmdb.auth_domain.usecases

import arrow.core.Either
import com.davidluna.tmdb.core_domain.entities.Session
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import javax.inject.Inject

class CreateGuestSessionIdUseCase @Inject constructor(
    private val repository: SessionRepository,
) {

    suspend operator fun invoke(): Either<AppError, com.davidluna.tmdb.core_domain.entities.Session> =
        repository.createGuestSession()
}