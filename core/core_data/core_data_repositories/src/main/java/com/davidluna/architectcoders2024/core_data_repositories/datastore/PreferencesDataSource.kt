package com.davidluna.architectcoders2024.core_data_repositories.datastore

import com.davidluna.architectcoders2024.core_domain.core_entities.ContentKind
import com.davidluna.architectcoders2024.core_domain.core_entities.Session
import com.davidluna.architectcoders2024.core_domain.core_entities.UserAccount
import kotlinx.coroutines.flow.Flow

interface PreferencesDataSource {
    val session: Flow<Session>
    val userAccount: Flow<UserAccount>
    val contentKind: Flow<ContentKind>
    suspend fun closeSession(): Boolean
    suspend fun saveSession(session: Session): Boolean
    suspend fun saveUser(user: UserAccount): Boolean
    suspend fun saveContentKind(contentKind: ContentKind): Boolean
}