package com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session

import arrow.core.Either
import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import com.davidluna.architectcoders2024.core_domain.core_entities.UserAccount
import javax.inject.Inject

class GetUserAccountUseCase @Inject constructor(
    private val repository: SessionRepository
) {
    suspend operator fun invoke(): Either<AppError, UserAccount> =
        repository.getUserAccount()
}