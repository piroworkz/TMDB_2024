package com.davidluna.tmdb.core_domain.usecases.datastore

import com.davidluna.tmdb.core_domain.entities.ContentKind
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetContentKindUseCase @Inject constructor(private val repository: PreferencesRepository) {
    operator fun invoke(): Flow<com.davidluna.tmdb.core_domain.entities.ContentKind> = repository.contentKind
}
