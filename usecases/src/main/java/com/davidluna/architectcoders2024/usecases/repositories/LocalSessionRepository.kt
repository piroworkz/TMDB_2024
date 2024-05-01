package com.davidluna.architectcoders2024.usecases.repositories

import com.davidluna.architectcoders2024.domain.session.UserAccount
import kotlinx.coroutines.flow.Flow

interface LocalSessionRepository {
    val sessionId: Flow<String>
    val userAccount: Flow<UserAccount>
    val isGuest: Flow<Boolean>
    suspend fun closeSession()
}