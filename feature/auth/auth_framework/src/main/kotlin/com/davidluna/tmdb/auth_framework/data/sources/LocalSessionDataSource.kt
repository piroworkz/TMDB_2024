package com.davidluna.tmdb.auth_framework.data.sources

import com.davidluna.tmdb.auth_framework.data.local.database.dao.SessionDao
import com.davidluna.tmdb.auth_framework.data.local.database.entities.RoomSession
import com.davidluna.tmdb.auth_domain.entities.Session
import com.davidluna.tmdb.auth_domain.usecases.GetSessionUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalSessionDataSource @Inject constructor(private val local: SessionDao) : GetSessionUseCase {
    override val flow: Flow<Session?>
        get() = local.getSession().map { it?.toDomain() }

    private fun RoomSession.toDomain(): Session {
        return Session(
            sessionId = sessionId,
            isGuest = isGuest,
            expiresAt = expiresAt
        )
    }
}