package com.davidluna.architectcoders2024.usecases.auth

import arrow.core.Either
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.domain.session.UserAccount
import com.davidluna.architectcoders2024.usecases.repositories.AuthenticationRepository
import javax.inject.Inject

class GetUserAccountUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) {
    suspend operator fun invoke(): Either<AppError, UserAccount> =
        repository.getUserAccount()
}