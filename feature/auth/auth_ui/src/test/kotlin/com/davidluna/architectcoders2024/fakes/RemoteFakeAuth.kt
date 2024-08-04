package com.davidluna.architectcoders2024.fakes

import com.davidluna.architectcoders2024.auth_framework.data.remote.model.RemoteGuestSession
import com.davidluna.architectcoders2024.auth_framework.data.remote.model.RemoteSessionIdResponse
import com.davidluna.architectcoders2024.auth_framework.data.remote.model.RemoteTokenResponse
import com.davidluna.architectcoders2024.auth_framework.data.remote.model.RemoteUserAccountDetail
import com.davidluna.architectcoders2024.test_shared.reader.MockFiles
import com.davidluna.architectcoders2024.test_shared.reader.Reader

val fakeRemoteUserAccount: RemoteUserAccountDetail =
    Reader.fromJson(MockFiles.USER_ACCOUNT)

val fakeRemoteTokenResponse: RemoteTokenResponse =
    Reader.fromJson(MockFiles.AUTH_TOKEN_NEW)

val fakeRemoteSessionId: RemoteSessionIdResponse =
    Reader.fromJson(MockFiles.AUTH_SESSION_NEW)

val fakeRemoteGuestSession: RemoteGuestSession =
    Reader.fromJson(MockFiles.AUTH_GUEST_SESSION)