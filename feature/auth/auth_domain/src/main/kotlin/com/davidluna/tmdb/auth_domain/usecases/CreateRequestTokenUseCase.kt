package com.davidluna.tmdb.auth_domain.usecases

class CreateRequestTokenUseCase (private val repository: SessionRepository) {
    suspend operator fun invoke() = repository.createRequestToken()
}