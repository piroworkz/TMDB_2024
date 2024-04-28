package com.davidluna.architectcoders2024.usecases.auth

import com.davidluna.architectcoders2024.usecases.repositories.LocalSessionRepository
import javax.inject.Inject

class CloseSessionUseCase @Inject constructor(private val repository: LocalSessionRepository) {
    suspend operator fun invoke() = repository.closeSession()
}