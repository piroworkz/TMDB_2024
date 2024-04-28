package com.davidluna.architectcoders2024.usecases.auth

import com.davidluna.architectcoders2024.usecases.repositories.AuthenticationRepository
import javax.inject.Inject

class CreateRequestTokenUseCase @Inject constructor(private val repository: AuthenticationRepository) {
    suspend operator fun invoke() = repository.createRequestToken()
}