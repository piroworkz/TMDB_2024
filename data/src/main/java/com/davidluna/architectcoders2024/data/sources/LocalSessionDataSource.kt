package com.davidluna.architectcoders2024.data.sources

import com.davidluna.architectcoders2024.domain.session.SessionId
import com.davidluna.architectcoders2024.domain.session.UserAccount
import kotlinx.coroutines.flow.Flow

interface LocalSessionDataSource {
    val sessionId: Flow<SessionId>
    val userAccount: Flow<UserAccount>
    suspend fun saveSessionId(sessionId: String)
    suspend fun saveUser(user: UserAccount)
    suspend fun closeSession()
}