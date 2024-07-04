package com.davidluna.architectcoders2024.core_domain.core_usecases.datastore

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SessionIdUseCase @Inject constructor(private val repository: LocalPreferencesRepository) {
    operator fun invoke(): Flow<String> = repository.sessionId
}
