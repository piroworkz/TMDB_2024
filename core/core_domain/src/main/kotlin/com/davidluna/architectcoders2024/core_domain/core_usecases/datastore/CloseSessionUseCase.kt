package com.davidluna.architectcoders2024.core_domain.core_usecases.datastore

import javax.inject.Inject

class CloseSessionUseCase @Inject constructor(private val repository: LocalPreferencesRepository) {
    suspend operator fun invoke(): Boolean = repository.closeSession()
}