package com.davidluna.tmdb.core_domain.repositories

import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    val session: Flow<com.davidluna.tmdb.core_domain.entities.Session>
    val userAccount: Flow<com.davidluna.tmdb.core_domain.entities.UserAccount>
    val contentKind: Flow<com.davidluna.tmdb.core_domain.entities.ContentKind>
    suspend fun closeSession(): Boolean
    suspend fun saveContentKind(contentKind: com.davidluna.tmdb.core_domain.entities.ContentKind) : Boolean
}