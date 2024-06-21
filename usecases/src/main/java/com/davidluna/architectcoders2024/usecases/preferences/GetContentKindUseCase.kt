package com.davidluna.architectcoders2024.usecases.preferences

import com.davidluna.architectcoders2024.domain.ContentKind
import com.davidluna.architectcoders2024.usecases.repositories.LocalPreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetContentKindUseCase @Inject constructor(private val repository: LocalPreferencesRepository) {
    suspend operator fun invoke(): Flow<ContentKind> = repository.contentKind
}


