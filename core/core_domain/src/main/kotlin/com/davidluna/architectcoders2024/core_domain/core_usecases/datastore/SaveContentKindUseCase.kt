package com.davidluna.architectcoders2024.core_domain.core_usecases.datastore

import com.davidluna.architectcoders2024.core_domain.core_entities.ContentKind
import javax.inject.Inject

class SaveContentKindUseCase @Inject constructor(private val repository: LocalPreferencesRepository) {
    suspend operator fun invoke(contentKind: ContentKind): Boolean =
        repository.saveContentKind(contentKind)
}