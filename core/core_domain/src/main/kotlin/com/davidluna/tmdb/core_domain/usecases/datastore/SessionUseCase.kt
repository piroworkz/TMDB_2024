package com.davidluna.tmdb.core_domain.usecases.datastore

import com.davidluna.tmdb.core_domain.entities.Session
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SessionUseCase @Inject constructor(
    private val repository: PreferencesRepository
) {
    operator fun invoke(): Flow<com.davidluna.tmdb.core_domain.entities.Session> = repository.session
}
