package com.davidluna.architectcoders2024.app.data.local.datastore

import androidx.datastore.core.DataStore
import com.davidluna.protodatastore.AuthenticationValues
import com.davidluna.protodatastore.Session
import com.davidluna.protodatastore.UserAccount
import com.davidluna.protodatastore.copy
import kotlinx.coroutines.flow.map

class SessionDatastore(
    private val session: DataStore<Session>
) {

    fun getAuth() = session.data.map {
        it.auth
    }

    fun getUser() = session.data.map {
        it.user
    }

    suspend fun saveSessionId(sessionId: String) {
        session.updateData { it.copy { auth = auth.copy { this.sessionId = sessionId } } }
    }

    suspend fun saveToken(requestToken: String) {
        session.updateData { it.copy { auth = auth.copy { this.token = requestToken } } }
    }

    suspend fun saveAuth(auth: AuthenticationValues) {
        session.updateData { it.copy { this.auth = auth } }
    }

    suspend fun saveUser(user: UserAccount) {
        session.updateData { it.copy { this.user = user } }
    }

    suspend fun closeSession() {
        session.updateData { Session.getDefaultInstance() }
    }

}