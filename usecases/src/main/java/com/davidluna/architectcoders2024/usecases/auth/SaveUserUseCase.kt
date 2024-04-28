package com.davidluna.architectcoders2024.usecases.auth

import com.davidluna.architectcoders2024.domain.session.UserAccount
import com.davidluna.architectcoders2024.usecases.repositories.LocalSessionRepository
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(private val repository: LocalSessionRepository) {
    suspend operator fun invoke(user: UserAccount) = repository.saveUser(user)
}