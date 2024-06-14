package com.davidluna.architectcoders2024.usecases.session

import com.davidluna.architectcoders2024.usecases.repositories.SessionRepository
import javax.inject.Inject

class CreateRequestTokenUseCase @Inject constructor(private val repository: SessionRepository) {
    suspend operator fun invoke() = repository.createRequestToken()
}