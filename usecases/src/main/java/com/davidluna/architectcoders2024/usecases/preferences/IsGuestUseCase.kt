package com.davidluna.architectcoders2024.usecases.preferences

import com.davidluna.architectcoders2024.usecases.repositories.LocalPreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsGuestUseCase @Inject constructor(private val repository: LocalPreferencesRepository) {
    operator fun invoke(): Flow<Boolean> = repository.isGuest
}

