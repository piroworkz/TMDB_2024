package com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session

import javax.inject.Inject

class CreateRequestTokenUseCase @Inject constructor(private val repository: SessionRepository) {
    suspend operator fun invoke() = repository.createRequestToken()
}