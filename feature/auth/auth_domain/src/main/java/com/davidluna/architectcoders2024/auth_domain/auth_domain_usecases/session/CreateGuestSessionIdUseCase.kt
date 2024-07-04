package com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session

import arrow.core.Either
import com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session.GuestSession
import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.SessionRepository
import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import javax.inject.Inject

class CreateGuestSessionIdUseCase @Inject constructor(
    private val repository: SessionRepository,
) {

    suspend operator fun invoke(): Either<AppError, GuestSession> =
        repository.createGuestSessionId()
}