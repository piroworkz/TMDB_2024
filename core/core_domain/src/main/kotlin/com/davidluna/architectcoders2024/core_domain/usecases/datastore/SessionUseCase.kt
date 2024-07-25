package com.davidluna.architectcoders2024.core_domain.usecases.datastore

import com.davidluna.architectcoders2024.core_domain.entities.Session
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SessionUseCase @Inject constructor(
    private val repository: PreferencesRepository
) {
    operator fun invoke(): Flow<Session> = repository.session
}
