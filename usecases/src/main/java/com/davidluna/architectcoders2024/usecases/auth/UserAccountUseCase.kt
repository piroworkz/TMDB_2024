package com.davidluna.architectcoders2024.usecases.auth

import com.davidluna.architectcoders2024.domain.session.UserAccount
import com.davidluna.architectcoders2024.usecases.repositories.LocalSessionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserAccountUseCase @Inject constructor(private val repository: LocalSessionRepository) {
    operator fun invoke(): Flow<UserAccount> = repository.userAccount
}