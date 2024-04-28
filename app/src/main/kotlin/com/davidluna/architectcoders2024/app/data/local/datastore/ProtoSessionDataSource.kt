package com.davidluna.architectcoders2024.app.data.local.datastore

import androidx.datastore.core.DataStore
import com.davidluna.architectcoders2024.data.sources.LocalSessionDataSource
import com.davidluna.architectcoders2024.domain.session.SessionId
import com.davidluna.architectcoders2024.domain.session.UserAccount
import com.davidluna.protodatastore.ProtoSession
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class ProtoSessionDataSource @Inject constructor(
    private val dataStore: DataStore<ProtoSession>
) : LocalSessionDataSource {

    override val sessionId: Flow<SessionId>
        get() = dataStore.data.map {
            it.auth.toDomain()
        }

    override val userAccount: Flow<UserAccount>
        get() = dataStore.data.map {
            it.user.toDomain()
        }

    override suspend fun saveSessionId(sessionId: String) {
        dataStore.updateData { it.toProto(sessionId) }
    }

    override suspend fun saveUser(user: UserAccount) {
        dataStore.updateData { it.toProto(user) }
    }

    override suspend fun closeSession() {
        dataStore.updateData { ProtoSession.getDefaultInstance() }
    }
}
