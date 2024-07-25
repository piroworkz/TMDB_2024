package com.davidluna.architectcoders2024.core_domain.usecases.datastore

import com.davidluna.architectcoders2024.core_domain.entities.ContentKind
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetContentKindUseCase @Inject constructor(private val repository: PreferencesRepository) {
    operator fun invoke(): Flow<ContentKind> = repository.contentKind
}
