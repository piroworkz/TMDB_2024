package com.davidluna.architectcoders2024.core_data_repositories.datastore

import com.davidluna.architectcoders2024.core_domain.core_entities.ContentKind
import com.davidluna.architectcoders2024.core_domain.core_entities.session.UserAccount
import kotlinx.coroutines.flow.Flow

interface PreferencesDataSource {
    val sessionId: Flow<String>
    val userAccount: Flow<UserAccount>
    val isGuest: Flow<Boolean>
    val contentKind: Flow<ContentKind>
    suspend fun closeSession(): Boolean
    suspend fun saveIsGuest(isGuest: Boolean): Boolean
    suspend fun saveSessionId(sessionId: String): Boolean
    suspend fun saveUser(user: UserAccount): Boolean
    suspend fun saveContentKind(contentKind: ContentKind): Boolean
}