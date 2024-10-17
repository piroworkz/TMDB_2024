package com.davidluna.tmdb.auth_domain.usecases

import com.davidluna.tmdb.auth_domain.repositories.SessionRepository

class CreateRequestTokenUseCase (private val repository: SessionRepository) {
    suspend operator fun invoke() = repository.createRequestToken()
}