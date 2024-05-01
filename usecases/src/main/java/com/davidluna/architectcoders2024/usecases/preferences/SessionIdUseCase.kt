package com.davidluna.architectcoders2024.usecases.preferences

import com.davidluna.architectcoders2024.usecases.repositories.LocalSessionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SessionIdUseCase @Inject constructor(private val repository: LocalSessionRepository) {
    operator fun invoke(): Flow<String> = repository.sessionId
}
