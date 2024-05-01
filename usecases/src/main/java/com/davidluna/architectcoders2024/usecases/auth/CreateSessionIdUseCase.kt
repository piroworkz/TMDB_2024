package com.davidluna.architectcoders2024.usecases.auth

import arrow.core.Either
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.domain.requests.LoginRequest
import com.davidluna.architectcoders2024.domain.session.SessionId
import com.davidluna.architectcoders2024.usecases.repositories.AuthenticationRepository
import javax.inject.Inject

class CreateSessionIdUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) {
    suspend operator fun invoke(loginRequest: LoginRequest): Either<AppError, SessionId> =
        repository.createSessionId(loginRequest)
}