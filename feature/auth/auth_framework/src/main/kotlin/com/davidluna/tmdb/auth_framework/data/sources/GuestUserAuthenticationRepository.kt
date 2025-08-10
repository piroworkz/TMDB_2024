package com.davidluna.tmdb.auth_framework.data.sources

import arrow.core.Either
import com.davidluna.tmdb.auth_domain.usecases.LoginAsGuest
import com.davidluna.tmdb.auth_framework.data.local.database.dao.SessionDao
import com.davidluna.tmdb.auth_framework.data.local.database.entities.RoomSession
import com.davidluna.tmdb.auth_framework.data.remote.RemoteAuthenticationService
import com.davidluna.tmdb.auth_framework.data.remote.model.RemoteGuestSession
import com.davidluna.tmdb.core_domain.entities.AppError
import com.davidluna.tmdb.core_domain.entities.tryCatch
import com.davidluna.tmdb.core_framework.data.remote.model.toAppError
import javax.inject.Inject

class GuestUserAuthenticationRepository @Inject constructor(
    private val remote: RemoteAuthenticationService,
    private val local: SessionDao
) : LoginAsGuest {

    override suspend fun invoke(): Either<AppError, Unit> = tryCatch {
        remote.createGuestSession().fold(
            ifLeft = { throw it.toAppError() },
            ifRight = { local.insertSession(session = it.toLocalStorage()) }
        )
    }

    private fun RemoteGuestSession.toLocalStorage(): RoomSession = RoomSession(
        sessionId = guestSessionId,
        isGuest = success,
        expiresAt = expiresAt
    )
}