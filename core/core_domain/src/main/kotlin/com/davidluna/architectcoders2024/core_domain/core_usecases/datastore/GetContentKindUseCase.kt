package com.davidluna.architectcoders2024.core_domain.core_usecases.datastore

import com.davidluna.architectcoders2024.core_domain.core_entities.ContentKind
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetContentKindUseCase @Inject constructor(private val repository: LocalPreferencesRepository) {
    operator fun invoke(): Flow<ContentKind> = repository.contentKind
}
