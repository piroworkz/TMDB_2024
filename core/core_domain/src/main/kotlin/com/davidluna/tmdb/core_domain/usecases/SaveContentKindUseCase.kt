package com.davidluna.tmdb.core_domain.usecases

import com.davidluna.tmdb.core_domain.repositories.PreferencesRepository

class SaveContentKindUseCase (private val repository: PreferencesRepository) {
    suspend operator fun invoke(contentKind: com.davidluna.tmdb.core_domain.entities.ContentKind) =
        repository.saveContentKind(contentKind)
}