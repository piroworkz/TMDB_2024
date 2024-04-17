package com.davidluna.architectcoders2024.app.data.remote

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class MoviesInterceptor() : Interceptor, SessionProvider {


    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.newBuilder().apply {
            if (sessionId.isNotEmpty()) {
                addQueryParameter("session_id", sessionId)
            }
            addQueryParameter("api_key", API_KEY)
        }.build()

        val newRequest = request.newBuilder().apply {
            addHeader("Authorization", "api_key $API_KEY")
            url(url)
        }.build()


        return chain.proceed(newRequest)
    }

    companion object {
        private var sessionId: String = ""
        private const val API_KEY = "2a00202b4e3679575440424cbfe2c97b"
    }

    override suspend fun setSessionId(sessionId: String) {
        Log.d("<-- MoviesInterceptor", "setSessionId: $sessionId")
        MoviesInterceptor.sessionId = sessionId
    }

    override suspend fun setGuestId(guestId: String) {

    }
}

