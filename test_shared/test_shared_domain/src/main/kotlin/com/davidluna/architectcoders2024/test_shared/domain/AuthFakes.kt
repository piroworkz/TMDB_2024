package com.davidluna.architectcoders2024.test_shared.domain

import com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session.LoginRequest
import com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session.QueryArgs
import com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session.TokenResponse
import com.davidluna.architectcoders2024.core_domain.core_entities.ContentKind
import com.davidluna.architectcoders2024.core_domain.core_entities.GuestSession
import com.davidluna.architectcoders2024.core_domain.core_entities.Session
import com.davidluna.architectcoders2024.core_domain.core_entities.SessionId
import com.davidluna.architectcoders2024.core_domain.core_entities.UserAccount

const val FAKE_QUERY_PARAMS =
    "?request_token=e0efa7d264a599220d2ad42df936a735d4319054&approved=true"
const val FAKE_REQUEST_TOKEN = "e0efa7d264a599220d2ad42df936a735d4319054"
const val FAKE_SESSION_ID = "e0efaf145sdljg64sdg53g13fd"

val fakeUserAccount: UserAccount = UserAccount(
    id = 8892,
    name = "Louise Rose",
    username = "Jennifer Snider",
    avatarPath = "auctor"
)
val fakeTokenResponse: TokenResponse = TokenResponse(
    expiresAt = "tomorrow",
    requestToken = FAKE_REQUEST_TOKEN,
    success = true
)

val fakeSession: Session = Session(
    id = FAKE_SESSION_ID,
    guestSession = GuestSession(id = "", isGuest = false, expiresAt = "")
)

val fakeEmptySession =
    Session(
        id = "",
        guestSession = GuestSession(id = "", isGuest = false, expiresAt = "")
    )

val guestSessionFake = GuestSession(id = FAKE_SESSION_ID, isGuest = true, expiresAt = "12/13/24")

val fakeGuestSession = fakeSession.copy(id = "", guestSession = guestSessionFake)

val fakeSessionId = SessionId(sessionId = FAKE_SESSION_ID)

val fakeLoginRequest = LoginRequest(requestToken = FAKE_REQUEST_TOKEN)

val fakeContentKind = ContentKind.MOVIE

val fakeEmptyQueryArgs = QueryArgs(
    requestToken = "", approved = false
)

val fakeQueryArgs = QueryArgs(
    requestToken = FAKE_REQUEST_TOKEN, approved = true
)


