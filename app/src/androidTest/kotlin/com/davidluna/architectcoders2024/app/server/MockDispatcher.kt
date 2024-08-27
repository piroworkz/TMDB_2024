package com.davidluna.architectcoders2024.app.server

import com.davidluna.architectcoders2024.test_shared.reader.TestConstants.AUTH_GUEST_SESSION
import com.davidluna.architectcoders2024.test_shared.reader.TestConstants.AUTH_SESSION_NEW
import com.davidluna.architectcoders2024.test_shared.reader.TestConstants.AUTH_TOKEN_NEW
import com.davidluna.architectcoders2024.test_shared.reader.TestConstants.REMOTE_ERROR
import com.davidluna.architectcoders2024.test_shared.reader.TestConstants.USER_ACCOUNT
import com.davidluna.architectcoders2024.test_shared.reader.Reader
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class MockDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse =
        getResponse(request.path)

    private fun getResponse(path: String?): MockResponse {
        if (path.isNullOrEmpty()) {
            return fromFile(REMOTE_ERROR)
        }

        val fileName = when {
            path.contains("token/new") -> AUTH_TOKEN_NEW
            path.contains("session/new") -> AUTH_SESSION_NEW
            path.contains("guest_session/new") -> AUTH_GUEST_SESSION
            path.contains("account") -> USER_ACCOUNT
            else -> REMOTE_ERROR
        }
        return fromFile(fileName)
    }


    private fun fromFile(fileName: String): MockResponse {
        return try {
            MockResponse().setBody(Reader.fromFile(fileName))
        } catch (e: Exception) {
            MockResponse().setBody(Reader.fromFile(REMOTE_ERROR))
        }
    }
}