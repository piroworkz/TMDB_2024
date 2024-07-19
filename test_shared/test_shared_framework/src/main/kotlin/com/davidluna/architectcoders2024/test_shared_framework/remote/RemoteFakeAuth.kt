package com.davidluna.architectcoders2024.test_shared_framework.remote

import com.davidluna.architectcoders2024.auth_data_framework.remote.model.authentication.RemoteGuestSession
import com.davidluna.architectcoders2024.auth_data_framework.remote.model.authentication.RemoteSessionIdResponse
import com.davidluna.architectcoders2024.auth_data_framework.remote.model.authentication.RemoteTokenResponse
import com.davidluna.architectcoders2024.auth_data_framework.remote.model.authentication.RemoteUserAccountDetail
import com.davidluna.architectcoders2024.test_shared_framework.utils.MockFiles
import com.davidluna.architectcoders2024.test_shared_framework.utils.Reader

val fakeRemoteUserAccount: RemoteUserAccountDetail =
    Reader.fromJson(MockFiles.USER_ACCOUNT)

val fakeRemoteTokenResponse: RemoteTokenResponse =
    Reader.fromJson(MockFiles.AUTH_TOKEN_NEW)

val fakeRemoteSessionId: RemoteSessionIdResponse =
    Reader.fromJson(MockFiles.AUTH_SESSION_NEW)

val fakeRemoteGuestSession: RemoteGuestSession =
    Reader.fromJson(MockFiles.AUTH_GUEST_SESSION)