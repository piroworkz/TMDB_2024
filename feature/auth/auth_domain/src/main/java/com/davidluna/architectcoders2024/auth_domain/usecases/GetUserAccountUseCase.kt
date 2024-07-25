package com.davidluna.architectcoders2024.auth_domain.usecases

import arrow.core.Either
import com.davidluna.architectcoders2024.core_domain.entities.UserAccount
import com.davidluna.architectcoders2024.core_domain.entities.errors.AppError
import javax.inject.Inject

class GetUserAccountUseCase @Inject constructor(
    private val repository: SessionRepository
) {
    suspend operator fun invoke(): Either<AppError, UserAccount> =
        repository.getUserAccount()
}