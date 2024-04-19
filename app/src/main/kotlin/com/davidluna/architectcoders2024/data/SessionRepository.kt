package com.davidluna.architectcoders2024.data

import com.davidluna.architectcoders2024.app.data.local.datastore.SessionDatastore
import com.davidluna.protodatastore.AuthenticationValues
import com.davidluna.protodatastore.UserAccount
import kotlinx.coroutines.flow.Flow

class SessionRepository(
    private val local: SessionDatastore
) {
    val auth: Flow<AuthenticationValues>
        get() = local.getAuth()

    val user: Flow<UserAccount>
        get() = local.getUser()

    suspend fun saveSessionId(sessionId: String) = local.saveSessionId(sessionId)

    suspend fun saveToken(requestToken: String) = local.saveToken(requestToken)

    suspend fun saveAuth(auth: AuthenticationValues) = local.saveAuth(auth)

    suspend fun saveUser(user: UserAccount) = local.saveUser(user)

    suspend fun closeSession() = local.closeSession()

}