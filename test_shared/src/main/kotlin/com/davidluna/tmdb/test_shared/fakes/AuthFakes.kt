package com.davidluna.tmdb.test_shared.fakes

import com.davidluna.tmdb.auth_domain.entities.session.LoginRequest
import com.davidluna.tmdb.auth_domain.entities.session.QueryArgs
import com.davidluna.tmdb.auth_domain.entities.session.TokenResponse
import com.davidluna.tmdb.core_domain.entities.ContentKind
import com.davidluna.tmdb.core_domain.entities.GuestSession
import com.davidluna.tmdb.core_domain.entities.Session
import com.davidluna.tmdb.core_domain.entities.SessionId
import com.davidluna.tmdb.core_domain.entities.UserAccount

const val FAKE_QUERY_PARAMS =
    "?request_token=e0efa7d264a599220d2ad42df936a735d4319054&approved=true"
const val FAKE_TMDB_URL =
    "https://www.themoviedb.org/authenticate/a84c69ad82b2759b5fa9e52ab11f788de81cb464?redirect_to=https://deeplinks-d440b.web.app/login/"
const val FAKE_DEEP_LINK = "https://deeplinks-d440b.web.app/login/$FAKE_QUERY_PARAMS"
const val FAKE_REQUEST_TOKEN = "e0efa7d264a599220d2ad42df936a735d4319054"
const val FAKE_SESSION_ID = "e0efaf145sdljg64sdg53g13fd"

val fakeUserAccount: com.davidluna.tmdb.core_domain.entities.UserAccount =
    com.davidluna.tmdb.core_domain.entities.UserAccount(
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

val fakeSession: com.davidluna.tmdb.core_domain.entities.Session =
    com.davidluna.tmdb.core_domain.entities.Session(
        id = FAKE_SESSION_ID,
        guestSession = com.davidluna.tmdb.core_domain.entities.GuestSession(id = "", expiresAt = "")
    )

val fakeEmptySession =
    com.davidluna.tmdb.core_domain.entities.Session(
        id = "",
        guestSession = com.davidluna.tmdb.core_domain.entities.GuestSession(id = "", expiresAt = "")
    )

val guestSessionFake = com.davidluna.tmdb.core_domain.entities.GuestSession(
    id = FAKE_SESSION_ID,
    expiresAt = "12/13/24"
)

val fakeGuestSession = fakeSession.copy(id = "", guestSession = guestSessionFake)

val fakeSessionId = com.davidluna.tmdb.core_domain.entities.SessionId(sessionId = FAKE_SESSION_ID)

val fakeLoginRequest = LoginRequest(requestToken = FAKE_REQUEST_TOKEN)

val fakeContentKind = com.davidluna.tmdb.core_domain.entities.ContentKind.MOVIE

val fakeEmptyQueryArgs = QueryArgs(
    requestToken = "", approved = false
)

val fakeQueryArgs = QueryArgs(
    requestToken = FAKE_REQUEST_TOKEN, approved = true
)

val fakeEmptyUserAccount = com.davidluna.tmdb.core_domain.entities.UserAccount(
    id = 0,
    name = "",
    username = "",
    avatarPath = ""
)


