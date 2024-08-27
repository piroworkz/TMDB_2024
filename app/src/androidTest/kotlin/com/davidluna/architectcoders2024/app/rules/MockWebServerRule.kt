package com.davidluna.architectcoders2024.app.rules

import com.davidluna.architectcoders2024.app.server.MockDispatcher
import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class MockWebServerRule: TestWatcher() {
    private val mockWebServer = MockWebServer()

    override fun starting(description: Description?) {
        mockWebServer.start(8080)
        mockWebServer.dispatcher = MockDispatcher()
    }

    override fun finished(description: Description?) {
        mockWebServer.shutdown()
    }

}