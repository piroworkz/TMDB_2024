package com.davidluna.tmdb.auth_domain.usecases

import javax.inject.Inject

class CreateRequestTokenUseCase @Inject constructor(private val repository: SessionRepository) {
    suspend operator fun invoke() = repository.createRequestToken()
}