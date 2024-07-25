package com.davidluna.architectcoders2024.core_domain.usecases.datastore

import com.davidluna.architectcoders2024.core_domain.entities.UserAccount
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserAccountUseCase @Inject constructor(private val repository: PreferencesRepository) {
    operator fun invoke(): Flow<UserAccount> = repository.userAccount
}