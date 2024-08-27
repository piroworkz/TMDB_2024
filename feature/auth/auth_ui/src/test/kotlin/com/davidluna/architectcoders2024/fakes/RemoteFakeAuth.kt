package com.davidluna.architectcoders2024.fakes

import com.davidluna.architectcoders2024.auth_framework.data.remote.model.RemoteGuestSession
import com.davidluna.architectcoders2024.auth_framework.data.remote.model.RemoteSessionIdResponse
import com.davidluna.architectcoders2024.auth_framework.data.remote.model.RemoteTokenResponse
import com.davidluna.architectcoders2024.auth_framework.data.remote.model.RemoteUserAccountDetail
import com.davidluna.architectcoders2024.test_shared.reader.TestConstants
import com.davidluna.architectcoders2024.test_shared.reader.Reader

val fakeRemoteUserAccount: RemoteUserAccountDetail =
    Reader.fromJson(TestConstants.USER_ACCOUNT)

val fakeRemoteTokenResponse: RemoteTokenResponse =
    Reader.fromJson(TestConstants.AUTH_TOKEN_NEW)

val fakeRemoteSessionId: RemoteSessionIdResponse =
    Reader.fromJson(TestConstants.AUTH_SESSION_NEW)

val fakeRemoteGuestSession: RemoteGuestSession =
    Reader.fromJson(TestConstants.AUTH_GUEST_SESSION)