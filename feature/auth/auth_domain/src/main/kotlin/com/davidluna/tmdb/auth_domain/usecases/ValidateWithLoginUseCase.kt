package com.davidluna.tmdb.auth_domain.usecases

import arrow.core.Either
import com.davidluna.tmdb.auth_domain.entities.session.LoginRequest
import com.davidluna.tmdb.core_domain.entities.Session
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import javax.inject.Inject

class CreateSessionUseCase @Inject constructor(
    private val repository: SessionRepository
) {
    suspend operator fun invoke(loginRequest: LoginRequest): Either<AppError, com.davidluna.tmdb.core_domain.entities.Session> =
        repository.createSessionId(loginRequest)
}