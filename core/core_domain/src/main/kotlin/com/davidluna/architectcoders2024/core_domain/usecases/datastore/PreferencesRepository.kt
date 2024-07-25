package com.davidluna.architectcoders2024.core_domain.usecases.datastore

import com.davidluna.architectcoders2024.core_domain.entities.ContentKind
import com.davidluna.architectcoders2024.core_domain.entities.Session
import com.davidluna.architectcoders2024.core_domain.entities.UserAccount
import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    val session: Flow<Session>
    val userAccount: Flow<UserAccount>
    val contentKind: Flow<ContentKind>
    suspend fun closeSession(): Boolean
    suspend fun saveContentKind(contentKind: ContentKind) : Boolean
}