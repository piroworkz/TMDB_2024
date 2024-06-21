package com.davidluna.architectcoders2024.data.sources.preferences

import com.davidluna.architectcoders2024.domain.ContentKind
import com.davidluna.architectcoders2024.domain.session.UserAccount
import kotlinx.coroutines.flow.Flow

interface PreferencesDataSource {
    val sessionId: Flow<String>
    val userAccount: Flow<UserAccount>
    val isGuest: Flow<Boolean>
    val contentKind: Flow<ContentKind>
    suspend fun closeSession()
    suspend fun saveIsGuest(isGuest: Boolean)
    suspend fun saveSessionId(sessionId: String)
    suspend fun saveUser(user: UserAccount)
    suspend fun saveContentKind(contentKind: ContentKind)
}