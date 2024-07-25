package com.davidluna.architectcoders2024.core_domain.usecases.datastore

import javax.inject.Inject

class CloseSessionUseCase @Inject constructor(private val repository: PreferencesRepository) {
    suspend operator fun invoke() = repository.closeSession()
}
