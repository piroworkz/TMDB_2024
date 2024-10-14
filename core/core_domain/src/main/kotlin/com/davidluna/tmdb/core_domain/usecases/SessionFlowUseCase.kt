package com.davidluna.tmdb.core_domain.usecases

import com.davidluna.tmdb.core_domain.entities.Session
import com.davidluna.tmdb.core_domain.repositories.PreferencesRepository
import kotlinx.coroutines.flow.Flow

class SessionFlowUseCase (private val repository: PreferencesRepository) {
    operator fun invoke(): Flow<Session> = repository.session
}
