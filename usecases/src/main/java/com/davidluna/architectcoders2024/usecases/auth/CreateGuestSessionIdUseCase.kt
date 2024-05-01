package com.davidluna.architectcoders2024.usecases.auth

import arrow.core.Either
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.domain.session.GuestSession
import com.davidluna.architectcoders2024.usecases.repositories.AuthenticationRepository
import javax.inject.Inject

class CreateGuestSessionIdUseCase @Inject constructor(
    private val repository: AuthenticationRepository,
) {

    suspend operator fun invoke(): Either<AppError, GuestSession> =
        repository.createGuestSessionId()
}