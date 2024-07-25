package com.davidluna.architectcoders2024.auth_domain.usecases

import arrow.core.Either
import com.davidluna.architectcoders2024.auth_domain.entities.session.LoginRequest
import com.davidluna.architectcoders2024.core_domain.entities.Session
import com.davidluna.architectcoders2024.core_domain.entities.errors.AppError
import javax.inject.Inject

class CreateSessionUseCase @Inject constructor(
    private val repository: SessionRepository
) {
    suspend operator fun invoke(loginRequest: LoginRequest): Either<AppError, Session> =
        repository.createSessionId(loginRequest)
}