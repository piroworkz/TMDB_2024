package com.davidluna.tmdb.core_domain.usecases.datastore

import com.davidluna.tmdb.core_domain.entities.ContentKind
import com.davidluna.tmdb.core_domain.entities.Session
import com.davidluna.tmdb.core_domain.entities.UserAccount
import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    val session: Flow<com.davidluna.tmdb.core_domain.entities.Session>
    val userAccount: Flow<com.davidluna.tmdb.core_domain.entities.UserAccount>
    val contentKind: Flow<com.davidluna.tmdb.core_domain.entities.ContentKind>
    suspend fun closeSession(): Boolean
    suspend fun saveContentKind(contentKind: com.davidluna.tmdb.core_domain.entities.ContentKind) : Boolean
}