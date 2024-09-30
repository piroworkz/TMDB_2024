package com.davidluna.tmdb.core_domain.usecases

import com.davidluna.tmdb.core_domain.repositories.PreferencesRepository

class CloseSessionUseCase (private val repository: PreferencesRepository) {
    suspend operator fun invoke() = repository.closeSession()
}
