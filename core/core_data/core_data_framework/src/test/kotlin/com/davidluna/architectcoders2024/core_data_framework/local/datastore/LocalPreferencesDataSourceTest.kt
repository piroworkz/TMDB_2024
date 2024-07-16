package com.davidluna.architectcoders2024.core_data_framework.local.datastore

import androidx.datastore.core.DataStore
import com.davidluna.architectcoders2024.core_data_repositories.datastore.PreferencesDataSource
import com.davidluna.architectcoders2024.core_domain.core_entities.ContentKind
import com.davidluna.architectcoders2024.core_domain.core_entities.UserAccount
import com.davidluna.protodatastore.ProtoPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FakeLocalPreferencesDataSource @Inject constructor(private val dataStore: DataStore<ProtoPreferences>) :
    PreferencesDataSource {

    override val sessionId: Flow<String>
        get() = TODO("Not yet implemented")
    override val userAccount: Flow<UserAccount>
        get() = TODO("Not yet implemented")
    override val contentKind: Flow<ContentKind>
        get() = TODO("Not yet implemented")

    override suspend fun closeSession(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun saveIsGuest(isGuest: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun saveSessionId(sessionId: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun saveUser(user: UserAccount): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun saveContentKind(contentKind: ContentKind): Boolean {
        TODO("Not yet implemented")
    }
}