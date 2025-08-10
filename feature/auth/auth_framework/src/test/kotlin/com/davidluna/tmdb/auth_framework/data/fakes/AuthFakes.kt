package com.davidluna.tmdb.auth_framework.data.fakes

import com.davidluna.tmdb.auth_domain.entities.LoginRequest
import com.davidluna.tmdb.auth_domain.entities.Session
import com.davidluna.tmdb.auth_domain.entities.UserAccount
import com.davidluna.tmdb.auth_framework.data.local.database.entities.RoomSession
import com.davidluna.tmdb.auth_framework.data.local.database.entities.RoomUserAccount
import com.davidluna.tmdb.auth_framework.data.remote.model.RemoteAvatar
import com.davidluna.tmdb.auth_framework.data.remote.model.RemoteGravatar
import com.davidluna.tmdb.auth_framework.data.remote.model.RemoteGuestSession
import com.davidluna.tmdb.auth_framework.data.remote.model.RemoteSessionIdResponse
import com.davidluna.tmdb.auth_framework.data.remote.model.RemoteTmdb
import com.davidluna.tmdb.auth_framework.data.remote.model.RemoteTokenResponse
import com.davidluna.tmdb.auth_framework.data.remote.model.RemoteUserAccountDetail
import com.davidluna.tmdb.core_framework.data.remote.model.RemoteError
import com.davidluna.tmdb.core_framework.data.remote.model.buildModel

val fakeRoomSession = RoomSession(
    id = 0,
    sessionId = "guest123",
    isGuest = true,
    expiresAt = "2025-12-01 00:00:00 UTC"
)

val fakeDomainSession = Session(
    sessionId = "guest123",
    isGuest = true,
    expiresAt = "2025-12-01 00:00:00 UTC"
)

val fakeRemoteUserAccountDetail = RemoteUserAccountDetail(
    avatar = RemoteAvatar(
        gravatar = RemoteGravatar(
            hash = "nihil"
        ),
        tmdb = RemoteTmdb(avatarPath = "wisi")
    ),
    userId = 3376,
    includeAdult = false,
    iso_3166_1 = "ferri",
    iso_639_1 = "sea",
    name = "Janna Strong",
    username = "Nell Delaney"
)

val fakeRoomAccount = with(fakeRemoteUserAccountDetail) {
    RoomUserAccount(
        userId = userId,
        name = name,
        username = username,
        avatarPath = avatar.tmdb.avatarPath.buildModel()
    )
}

val fakeDomainAccount = with(fakeRoomAccount) {
    UserAccount(
        userId = userId,
        name = name,
        username = username,
        avatarPath = avatarPath
    )
}

val fakeRemoteGuestSession = RemoteGuestSession(
    success = true,
    guestSessionId = "test_guest_session_id",
    expiresAt = "2024-12-31T23:59:59Z"
)

val fakeRemoteError = RemoteError(
    statusCode = 500,
    statusMessage = "some test error",
    success = false
)

val fakeRemoteTokenResponse = RemoteTokenResponse(
    expiresAt = "legimus",
    requestToken = "quot",
    success = true
)

val fakeLoginRequest = LoginRequest(
    username = "Michael Peck", password = "aperiri"
)

val fakeRemoteSessionIdResponse = RemoteSessionIdResponse(
    sessionId = "sociis",
    success = true
)