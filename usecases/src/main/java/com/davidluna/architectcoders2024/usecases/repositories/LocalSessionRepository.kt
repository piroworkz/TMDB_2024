package com.davidluna.architectcoders2024.usecases.repositories

import com.davidluna.architectcoders2024.domain.session.SessionId
import com.davidluna.architectcoders2024.domain.session.UserAccount
import kotlinx.coroutines.flow.Flow

interface LocalSessionRepository {
    val sessionId: Flow<SessionId>
    val userAccount: Flow<UserAccount>
    suspend fun saveSessionId(sessionId: String)
    suspend fun saveUser(user: UserAccount)
    suspend fun closeSession()
}