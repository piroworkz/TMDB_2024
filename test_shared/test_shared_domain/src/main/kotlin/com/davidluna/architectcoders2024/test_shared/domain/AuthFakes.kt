package com.davidluna.architectcoders2024.test_shared.domain

import com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session.GuestSession
import com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session.LoginRequest
import com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session.SessionId
import com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session.TokenResponse
import com.davidluna.architectcoders2024.core_domain.core_entities.ContentKind
import com.davidluna.architectcoders2024.core_domain.core_entities.UserAccount

val fakeUserAccount: UserAccount = UserAccount(
    id = 8892,
    name = "Louise Rose",
    username = "Jennifer Snider",
    avatarPath = "auctor"
)
val fakeTokenResponse: TokenResponse = TokenResponse(
    expiresAt = "dicat",
    requestToken = "discere",
    success = false
)
val fakeSessionId: SessionId = SessionId(sessionId = "efficitur")

val fakeGuestSession: GuestSession = GuestSession(expiresAt = "nam", guestSessionId = "pulvinar")

val fakeLoginRequest = LoginRequest(requestToken = "1654891231348")

val fakeContentKind = ContentKind.MOVIE
