package com.davidluna.architectcoders2024.core_data_repositories.datastore

import arrow.core.Either
import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import com.davidluna.architectcoders2024.core_domain.core_entities.ContentKind
import com.davidluna.architectcoders2024.core_domain.core_entities.UserAccount
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