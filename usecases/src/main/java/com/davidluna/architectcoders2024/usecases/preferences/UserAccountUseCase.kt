package com.davidluna.architectcoders2024.usecases.preferences

import com.davidluna.architectcoders2024.domain.session.UserAccount
import com.davidluna.architectcoders2024.usecases.repositories.LocalPreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserAccountUseCase @Inject constructor(private val repository: LocalPreferencesRepository) {
    operator fun invoke(): Flow<UserAccount> = repository.userAccount
}