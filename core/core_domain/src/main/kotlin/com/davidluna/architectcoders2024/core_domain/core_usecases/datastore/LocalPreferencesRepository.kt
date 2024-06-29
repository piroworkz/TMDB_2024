package com.davidluna.architectcoders2024.core_domain.core_usecases.datastore

import com.davidluna.architectcoders2024.core_domain.core_entities.ContentKind
import com.davidluna.architectcoders2024.core_domain.core_entities.UserAccount
import kotlinx.coroutines.flow.Flow

interface LocalPreferencesRepository {
    val sessionId: Flow<String>
    val userAccount: Flow<UserAccount>
    val isGuest: Flow<Boolean>
    val contentKind: Flow<ContentKind>
    suspend fun closeSession(): Boolean
    suspend fun saveContentKind(contentKind: ContentKind): Boolean
}