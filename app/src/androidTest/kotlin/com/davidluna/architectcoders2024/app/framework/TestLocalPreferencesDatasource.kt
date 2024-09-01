package com.davidluna.architectcoders2024.app.framework

import com.davidluna.architectcoders2024.core_domain.data.datastore.PreferencesDataSource
import com.davidluna.architectcoders2024.core_domain.entities.ContentKind
import com.davidluna.architectcoders2024.core_domain.entities.Session
import com.davidluna.architectcoders2024.core_domain.entities.UserAccount
import com.davidluna.architectcoders2024.core_framework.data.local.datastore.setContentKind
import com.davidluna.architectcoders2024.core_framework.data.local.datastore.setSession
import com.davidluna.architectcoders2024.core_framework.data.local.datastore.setUserAccount
import com.davidluna.architectcoders2024.core_framework.data.local.datastore.toDomain
import com.davidluna.protodatastore.ProtoPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TestLocalPreferencesDatasource @Inject constructor() : PreferencesDataSource {

    private val preferences = flowOf(ProtoPreferences.getDefaultInstance())

    override val session: Flow<Session>
        get() = preferences.map { it.toDomain() }
    override val userAccount: Flow<UserAccount>
        get() = preferences.map { it.user.toDomain() }
    override val contentKind: Flow<ContentKind>
        get() = preferences.map { ContentKind.valueOf(it.contentKind.name) }

    override suspend fun closeSession(): Boolean {
        preferences.map { ProtoPreferences.getDefaultInstance() }
        return true
    }

    override suspend fun saveSession(session: Session): Boolean {
        preferences.map { it.setSession(session) }
        return true
    }

    override suspend fun saveUser(user: UserAccount): Boolean {
        preferences.map { it.setUserAccount(user) }
        return true
    }

    override suspend fun saveContentKind(contentKind: ContentKind): Boolean {
        preferences.map { it.setContentKind(contentKind) }
        return true
    }
}