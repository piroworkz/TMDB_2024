package com.davidluna.architectcoders2024.auth_domain.usecases

import arrow.core.Either
import com.davidluna.architectcoders2024.core_domain.entities.Session
import com.davidluna.architectcoders2024.core_domain.entities.errors.AppError
import javax.inject.Inject

class CreateGuestSessionIdUseCase @Inject constructor(
    private val repository: SessionRepository,
) {

    suspend operator fun invoke(): Either<AppError, Session> =
        repository.createGuestSession()
}