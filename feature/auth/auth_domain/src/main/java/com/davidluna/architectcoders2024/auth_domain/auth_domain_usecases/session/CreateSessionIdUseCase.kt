package com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session

import arrow.core.Either
import com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session.LoginRequest
import com.davidluna.architectcoders2024.core_domain.core_entities.SessionId
import com.davidluna.architectcoders2024.core_domain.core_entities.errors.AppError
import javax.inject.Inject

class CreateSessionIdUseCase @Inject constructor(
    private val repository: SessionRepository
) {
    suspend operator fun invoke(loginRequest: LoginRequest): Either<AppError, SessionId> =
        repository.createSessionId(loginRequest)
}