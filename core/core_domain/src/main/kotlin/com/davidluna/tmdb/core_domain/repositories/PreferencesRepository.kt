package com.davidluna.tmdb.core_domain.repositories

import com.davidluna.tmdb.core_domain.entities.ContentKind
import com.davidluna.tmdb.core_domain.entities.Session
import com.davidluna.tmdb.core_domain.entities.UserAccount
import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    val session: Flow<Session>
    val userAccount: Flow<UserAccount>
    val contentKind: Flow<ContentKind>
    suspend fun closeSession(): Boolean
    suspend fun saveContentKind(contentKind: ContentKind) : Boolean
}