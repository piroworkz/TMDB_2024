package com.davidluna.architectcoders2024.app.data.local.datastore

import androidx.datastore.core.DataStore
import com.davidluna.architectcoders2024.data.sources.preferences.PreferencesDataSource
import com.davidluna.architectcoders2024.domain.ContentKind
import com.davidluna.architectcoders2024.domain.session.UserAccount
import com.davidluna.protodatastore.CONTENT_KIND
import com.davidluna.protodatastore.ProtoPreferences
import com.davidluna.protodatastore.copy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalPreferencesDataSource @Inject constructor(private val dataStore: DataStore<ProtoPreferences>) :
    PreferencesDataSource {

    override val sessionId: Flow<String>
        get() = dataStore.data.map {
            it.toDomain()
        }

    override val userAccount: Flow<UserAccount>
        get() = dataStore.data.map { it.user.toDomain() }

    override val isGuest: Flow<Boolean> =
        dataStore.data.map { it.session.isGuest }

    override val contentKind: Flow<ContentKind> =
        dataStore.data.map { ContentKind.valueOf(it.contentKind.name) }

    override suspend fun closeSession() {
        dataStore.updateData { ProtoPreferences.getDefaultInstance() }
    }

    override suspend fun saveIsGuest(isGuest: Boolean) {
        dataStore.updateData { preferences -> preferences.setIsGuest(isGuest) }
    }

    override suspend fun saveSessionId(sessionId: String) {
        dataStore.updateData { preferences -> preferences.setSessionId(sessionId) }
    }

    override suspend fun saveUser(user: UserAccount) {
        dataStore.updateData { preferences -> preferences.setUserAccount(user) }
    }

    override suspend fun saveContentKind(contentKind: ContentKind) {
        dataStore.updateData { preferences ->
            preferences.copy {
                this.contentKind = CONTENT_KIND.valueOf(contentKind.name)
            }
        }
    }
}

