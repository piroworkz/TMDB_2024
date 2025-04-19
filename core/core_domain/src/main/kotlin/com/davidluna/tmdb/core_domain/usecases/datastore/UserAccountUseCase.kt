package com.davidluna.tmdb.core_domain.usecases.datastore

import com.davidluna.tmdb.core_domain.entities.UserAccount
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserAccountUseCase @Inject constructor(private val repository: PreferencesRepository) {
    operator fun invoke(): Flow<com.davidluna.tmdb.core_domain.entities.UserAccount> = repository.userAccount
}