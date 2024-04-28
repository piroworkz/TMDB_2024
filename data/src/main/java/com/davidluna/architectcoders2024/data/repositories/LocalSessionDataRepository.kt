package com.davidluna.architectcoders2024.data.repositories

import com.davidluna.architectcoders2024.data.sources.LocalSessionDataSource
import com.davidluna.architectcoders2024.domain.session.SessionId
import com.davidluna.architectcoders2024.domain.session.UserAccount
import com.davidluna.architectcoders2024.usecases.repositories.LocalSessionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalSessionDataRepository @Inject constructor(
    private val local: LocalSessionDataSource
) : LocalSessionRepository {
    override val sessionId: Flow<SessionId>
        get() = local.sessionId

    override val userAccount: Flow<UserAccount>
        get() = local.userAccount

    override suspend fun saveSessionId(sessionId: String) = local.saveSessionId(sessionId)

    override suspend fun saveUser(user: UserAccount) = local.saveUser(user)

    override suspend fun closeSession() = local.closeSession()
}

