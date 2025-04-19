package com.davidluna.tmdb.core_domain.usecases.datastore

import com.davidluna.tmdb.core_domain.entities.ContentKind
import javax.inject.Inject

class SaveContentKindUseCase @Inject constructor(private val repository: PreferencesRepository) {
    suspend operator fun invoke(contentKind: com.davidluna.tmdb.core_domain.entities.ContentKind) =
        repository.saveContentKind(contentKind)
}