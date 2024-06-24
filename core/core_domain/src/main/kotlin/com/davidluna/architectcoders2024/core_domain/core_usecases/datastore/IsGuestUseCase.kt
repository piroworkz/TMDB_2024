package com.davidluna.architectcoders2024.core_domain.core_usecases.datastore

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsGuestUseCase @Inject constructor(private val repository: LocalPreferencesRepository) {
    operator fun invoke(): Flow<Boolean> = repository.isGuest
}

