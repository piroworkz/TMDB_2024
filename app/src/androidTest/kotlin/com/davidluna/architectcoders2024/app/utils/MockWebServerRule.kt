package com.davidluna.architectcoders2024.app.utils

import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestWatcher

class MockWebServerRule : TestWatcher() {
    private val server = MockWebServer()

    override fun starting(description: org.junit.runner.Description?) {
        super.starting(description)
        server.start(8080)
        server.dispatcher = MockWebServerDispatcher()
    }

    override fun finished(description: org.junit.runner.Description?) {
        super.finished(description)
        server.shutdown()
    }
}