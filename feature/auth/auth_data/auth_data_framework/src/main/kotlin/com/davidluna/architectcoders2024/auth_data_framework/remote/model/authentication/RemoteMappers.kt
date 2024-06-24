package com.davidluna.architectcoders2024.auth_data_framework.remote.model.authentication

import com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session.GuestSession
import com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session.LoginRequest
import com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session.SessionId
import com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session.TokenResponse
import com.davidluna.architectcoders2024.core_domain.core_entities.buildModel
import com.davidluna.architectcoders2024.core_domain.core_entities.session.UserAccount


fun RemoteUserAccountDetail.toDomain(): UserAccount = UserAccount(
    id = id,
    name = name,
    username = username,
    avatarPath = avatar.tmdb.avatarPath.buildModel()
)

fun RemoteSessionIdResponse.toDomain(): SessionId =
    SessionId(sessionId = sessionId)

fun LoginRequest.toRemote(): RemoteLoginRequest =
    RemoteLoginRequest(requestToken = requestToken)

fun RemoteTokenResponse.toDomain(): TokenResponse = TokenResponse(
    expiresAt = expiresAt,
    requestToken = requestToken,
    success = success
)

fun RemoteGuestSession.toDomain(): GuestSession = GuestSession(
    expiresAt = expiresAt,
    guestSessionId = guestSessionId
)
