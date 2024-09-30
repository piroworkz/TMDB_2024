package com.davidluna.tmdb.app.framework

import com.davidluna.tmdb.core_domain.data.datastore.PreferencesDataSource
import com.davidluna.tmdb.core_domain.entities.ContentKind
import com.davidluna.tmdb.core_domain.entities.Session
import com.davidluna.tmdb.core_domain.entities.UserAccount
import com.davidluna.tmdb.core_framework.data.local.datastore.setContentKind
import com.davidluna.tmdb.core_framework.data.local.datastore.setSession
import com.davidluna.tmdb.core_framework.data.local.datastore.setUserAccount
import com.davidluna.tmdb.core_framework.data.local.datastore.toDomain
import com.davidluna.protodatastore.ProtoPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map


class TestLocalPreferencesDatasource () :
    com.davidluna.tmdb.core_domain.data.datastore.PreferencesDataSource {

    private val preferences = flowOf(ProtoPreferences.getDefaultInstance())

    override val session: Flow<com.davidluna.tmdb.core_domain.entities.Session>
        get() = preferences.map { it.toDomain() }
    override val userAccount: Flow<com.davidluna.tmdb.core_domain.entities.UserAccount>
        get() = preferences.map { it.user.toDomain() }
    override val contentKind: Flow<com.davidluna.tmdb.core_domain.entities.ContentKind>
        get() = preferences.map { com.davidluna.tmdb.core_domain.entities.ContentKind.valueOf(it.contentKind.name) }

    override suspend fun closeSession(): Boolean {
        preferences.map { ProtoPreferences.getDefaultInstance() }
        return true
    }

    override suspend fun saveSession(session: com.davidluna.tmdb.core_domain.entities.Session): Boolean {
        preferences.map { it.setSession(session) }
        return true
    }

    override suspend fun saveUser(user: com.davidluna.tmdb.core_domain.entities.UserAccount): Boolean {
        preferences.map { it.setUserAccount(user) }
        return true
    }

    override suspend fun saveContentKind(contentKind: com.davidluna.tmdb.core_domain.entities.ContentKind): Boolean {
        preferences.map { it.setContentKind(contentKind) }
        return true
    }
}