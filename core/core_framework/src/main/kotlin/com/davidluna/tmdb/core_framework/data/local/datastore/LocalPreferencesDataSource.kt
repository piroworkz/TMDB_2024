package com.davidluna.tmdb.core_framework.data.local.datastore

import androidx.datastore.core.DataStore
import com.davidluna.tmdb.core_domain.data.datastore.PreferencesDataSource
import com.davidluna.tmdb.core_domain.entities.ContentKind
import com.davidluna.tmdb.core_domain.entities.Session
import com.davidluna.tmdb.core_domain.entities.UserAccount
import com.davidluna.tmdb.core_domain.entities.tryCatch
import com.davidluna.protodatastore.ProtoPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalPreferencesDataSource @Inject constructor(private val dataStore: DataStore<ProtoPreferences>) :
    com.davidluna.tmdb.core_domain.data.datastore.PreferencesDataSource {

    override val session: Flow<com.davidluna.tmdb.core_domain.entities.Session>
        get() = dataStore.data.map {
            it.toDomain()
        }

    override val userAccount: Flow<com.davidluna.tmdb.core_domain.entities.UserAccount>
        get() = dataStore.data.map { it.user.toDomain() }

    override val contentKind: Flow<com.davidluna.tmdb.core_domain.entities.ContentKind> = dataStore.data.map {
        com.davidluna.tmdb.core_domain.entities.ContentKind.valueOf(it.contentKind.name)
    }

    override suspend fun closeSession(): Boolean = tryCatch {
        dataStore.updateData { ProtoPreferences.getDefaultInstance() }
    }.isRight()

    override suspend fun saveSession(session: com.davidluna.tmdb.core_domain.entities.Session): Boolean = tryCatch {
        dataStore.updateData { preferences -> preferences.setSession(session) }
    }.isRight()

    override suspend fun saveUser(user: com.davidluna.tmdb.core_domain.entities.UserAccount): Boolean = tryCatch {
        dataStore.updateData { preferences -> preferences.setUserAccount(user) }
    }.isRight()

    override suspend fun saveContentKind(contentKind: com.davidluna.tmdb.core_domain.entities.ContentKind): Boolean = tryCatch {
        dataStore.updateData { preferences ->
            preferences.setContentKind(contentKind)
        }
    }.isRight()

}
