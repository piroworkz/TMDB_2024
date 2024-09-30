package com.davidluna.tmdb.core_domain.usecases

import com.davidluna.tmdb.core_domain.repositories.PreferencesRepository
import kotlinx.coroutines.flow.Flow

class GetContentKindUseCase (private val repository: PreferencesRepository) {
    operator fun invoke(): Flow<com.davidluna.tmdb.core_domain.entities.ContentKind> = repository.contentKind
}
