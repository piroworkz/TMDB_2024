package com.davidluna.architectcoders2024.app.data.remote

interface SessionProvider {
    suspend fun setSessionId(sessionId: String)

    suspend fun setGuestId(guestId: String)
}