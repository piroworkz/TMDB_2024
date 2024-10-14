package com.davidluna.tmdb.core_framework.data.local.datastore

import androidx.datastore.core.DataStore
import com.davidluna.protodatastore.ProtoPreferences
import com.davidluna.tmdb.core_domain.data.datastore.PreferencesDataSource
import com.davidluna.tmdb.core_domain.entities.ContentKind
import com.davidluna.tmdb.core_domain.entities.Session
import com.davidluna.tmdb.core_domain.entities.UserAccount
import com.davidluna.tmdb.core_domain.entities.tryCatch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalPreferencesDataSource (private val dataStore: DataStore<ProtoPreferences>) : PreferencesDataSource {

    override val session: Flow<Session>
        get() = dataStore.data.map(ProtoPreferences::toDomain)

    override val userAccount: Flow<UserAccount>
        get() = dataStore.data.map { it.user.toDomain() }

    override val contentKind: Flow<ContentKind> = dataStore.data.map {
        ContentKind.valueOf(it.contentKind.name)
    }

    override suspend fun closeSession(): Boolean = tryCatch {
        dataStore.updateData { ProtoPreferences.getDefaultInstance() }
    }.isRight()

    override suspend fun saveSession(session: Session): Boolean = tryCatch {
        dataStore.updateData { preferences -> preferences.setSession(session) }
    }.isRight()

    override suspend fun saveUser(user: UserAccount): Boolean = tryCatch {
        dataStore.updateData { preferences -> preferences.setUserAccount(user) }
    }.isRight()

    override suspend fun saveContentKind(contentKind: ContentKind): Boolean = tryCatch {
        dataStore.updateData { preferences ->
            preferences.setContentKind(contentKind)
        }
    }.isRight()

}
