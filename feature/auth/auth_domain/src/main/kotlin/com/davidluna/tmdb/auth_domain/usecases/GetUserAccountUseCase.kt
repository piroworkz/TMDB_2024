package com.davidluna.tmdb.auth_domain.usecases

import arrow.core.Either
import com.davidluna.tmdb.auth_domain.repositories.SessionRepository
import com.davidluna.tmdb.core_domain.entities.errors.AppError

class GetUserAccountUseCase (
    private val repository: SessionRepository
) {
    suspend operator fun invoke(): Either<AppError, com.davidluna.tmdb.core_domain.entities.UserAccount> =
        repository.getUserAccount()
}