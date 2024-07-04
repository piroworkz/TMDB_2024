package com.davidluna.architectcoders2024.core_data_framework.local.datastore

import androidx.datastore.core.DataStore
import com.davidluna.architectcoders2024.core_data_repositories.datastore.PreferencesDataSource
import com.davidluna.architectcoders2024.core_domain.core_entities.ContentKind
import com.davidluna.architectcoders2024.core_domain.core_entities.UserAccount
import com.davidluna.architectcoders2024.core_domain.core_entities.tryCatch
import com.davidluna.protodatastore.CONTENT_KIND
import com.davidluna.protodatastore.ProtoPreferences
import com.davidluna.protodatastore.copy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalPreferencesDataSource @Inject constructor(private val dataStore: DataStore<ProtoPreferences>) :
    PreferencesDataSource {

    override val sessionId: Flow<String>
        get() = dataStore.data.map { it.toDomain() }

    override val userAccount: Flow<UserAccount>
        get() = dataStore.data.map { it.user.toDomain() }

    override val isGuest: Flow<Boolean> =
        dataStore.data.map { it.session.isGuest }

    override val contentKind: Flow<ContentKind> =
        dataStore.data.map { ContentKind.valueOf(it.contentKind.name) }

    override suspend fun closeSession(): Boolean = tryCatch {
        dataStore.updateData { ProtoPreferences.getDefaultInstance() }
    }.isRight()

    override suspend fun saveIsGuest(isGuest: Boolean): Boolean = tryCatch {
        dataStore.updateData { preferences -> preferences.setIsGuest(isGuest) }
    }.isRight()

    override suspend fun saveSessionId(sessionId: String): Boolean = tryCatch {
        dataStore.updateData { preferences -> preferences.setSessionId(sessionId) }
    }.isRight()

    override suspend fun saveUser(user: UserAccount): Boolean = tryCatch {
        dataStore.updateData { preferences -> preferences.setUserAccount(user) }
    }.isRight()

    override suspend fun saveContentKind(contentKind: ContentKind): Boolean = tryCatch {
        dataStore.updateData { preferences ->
            preferences.copy {
                this.contentKind = CONTENT_KIND.valueOf(contentKind.name)
            }
        }
    }.isRight()
}

