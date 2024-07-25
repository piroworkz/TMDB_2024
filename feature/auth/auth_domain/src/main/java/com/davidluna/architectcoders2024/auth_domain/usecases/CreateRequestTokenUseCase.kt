package com.davidluna.architectcoders2024.auth_domain.usecases

import javax.inject.Inject

class CreateRequestTokenUseCase @Inject constructor(private val repository: SessionRepository) {
    suspend operator fun invoke() = repository.createRequestToken()
}