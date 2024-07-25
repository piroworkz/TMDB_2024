package com.davidluna.architectcoders2024.core_domain.usecases.datastore

import com.davidluna.architectcoders2024.core_domain.entities.ContentKind
import javax.inject.Inject

class SaveContentKindUseCase @Inject constructor(private val repository: PreferencesRepository) {
    suspend operator fun invoke(contentKind: ContentKind) =
        repository.saveContentKind(contentKind)
}