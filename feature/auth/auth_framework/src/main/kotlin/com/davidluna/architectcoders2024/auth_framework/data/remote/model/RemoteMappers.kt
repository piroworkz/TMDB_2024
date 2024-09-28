package com.davidluna.tmdb.auth_framework.data.remote.model

import com.davidluna.tmdb.auth_domain.entities.session.LoginRequest
import com.davidluna.tmdb.auth_domain.entities.session.TokenResponse
import com.davidluna.tmdb.core_domain.entities.GuestSession
import com.davidluna.tmdb.core_domain.entities.SessionId
import com.davidluna.tmdb.core_domain.entities.UserAccount
import com.davidluna.tmdb.core_domain.entities.buildModel


fun RemoteUserAccountDetail.toDomain(): com.davidluna.tmdb.core_domain.entities.UserAccount =
    com.davidluna.tmdb.core_domain.entities.UserAccount(
        id = id,
        name = name,
        username = username,
        avatarPath = avatar.tmdb.avatarPath.buildModel()
    )

fun RemoteSessionIdResponse.toDomain(): com.davidluna.tmdb.core_domain.entities.SessionId =
    com.davidluna.tmdb.core_domain.entities.SessionId(sessionId = sessionId)

fun LoginRequest.toRemote(): RemoteLoginRequest =
    RemoteLoginRequest(requestToken = requestToken)

fun RemoteTokenResponse.toDomain(): TokenResponse = TokenResponse(
    expiresAt = expiresAt,
    requestToken = requestToken,
    success = success
)

fun RemoteGuestSession.toDomain(): com.davidluna.tmdb.core_domain.entities.GuestSession =
    com.davidluna.tmdb.core_domain.entities.GuestSession(
        expiresAt = expiresAt,
        id = guestSessionId
    )
