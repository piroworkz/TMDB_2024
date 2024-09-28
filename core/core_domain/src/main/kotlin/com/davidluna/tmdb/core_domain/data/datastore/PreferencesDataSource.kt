package com.davidluna.tmdb.core_domain.data.datastore

import com.davidluna.tmdb.core_domain.entities.ContentKind
import com.davidluna.tmdb.core_domain.entities.Session
import com.davidluna.tmdb.core_domain.entities.UserAccount
import kotlinx.coroutines.flow.Flow

interface PreferencesDataSource {
    val session: Flow<com.davidluna.tmdb.core_domain.entities.Session>
    val userAccount: Flow<com.davidluna.tmdb.core_domain.entities.UserAccount>
    val contentKind: Flow<com.davidluna.tmdb.core_domain.entities.ContentKind>
    suspend fun closeSession(): Boolean
    suspend fun saveSession(session: com.davidluna.tmdb.core_domain.entities.Session): Boolean
    suspend fun saveUser(user: com.davidluna.tmdb.core_domain.entities.UserAccount): Boolean
    suspend fun saveContentKind(contentKind: com.davidluna.tmdb.core_domain.entities.ContentKind): Boolean
}