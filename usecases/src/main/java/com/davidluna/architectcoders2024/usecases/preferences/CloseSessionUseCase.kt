package com.davidluna.architectcoders2024.usecases.preferences

import com.davidluna.architectcoders2024.usecases.repositories.LocalPreferencesRepository
import javax.inject.Inject

class CloseSessionUseCase @Inject constructor(private val repository: LocalPreferencesRepository) {
    suspend operator fun invoke() = repository.closeSession()
}