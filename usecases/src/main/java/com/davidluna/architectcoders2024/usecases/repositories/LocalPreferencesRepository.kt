package com.davidluna.architectcoders2024.usecases.repositories

import com.davidluna.architectcoders2024.domain.ContentKind
import com.davidluna.architectcoders2024.domain.session.UserAccount
import kotlinx.coroutines.flow.Flow

interface LocalPreferencesRepository {
    val sessionId: Flow<String>
    val userAccount: Flow<UserAccount>
    val isGuest: Flow<Boolean>
    val contentKind: Flow<ContentKind>
    suspend fun closeSession()
    suspend fun saveContentKind(contentKind: ContentKind)
}