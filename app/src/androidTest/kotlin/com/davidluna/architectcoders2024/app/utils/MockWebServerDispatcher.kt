package com.davidluna.architectcoders2024.app.utils

import com.davidluna.architectcoders2024.test_shared.reader.MockFiles
import com.davidluna.architectcoders2024.test_shared.reader.Reader
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class MockWebServerDispatcher(
    private val movieId: String = "1022789",
    private val tvShowId: String = "209374",
) : Dispatcher() {


    override fun dispatch(request: RecordedRequest): MockResponse = fetchResponse(request.path)


    private fun fetchResponse(path: String?): MockResponse {
        if (path.isNullOrEmpty()) {
            return buildResponse(MockFiles.REMOTE_ERROR)
        }

        val filename: String = when {
            path.contains("/authentication/token/new") -> MockFiles.AUTH_TOKEN_NEW
            path.contains("/authentication/session/new") -> MockFiles.AUTH_SESSION_NEW
            path.contains("/authentication/guest_session/new") -> MockFiles.AUTH_GUEST_SESSION
            path.contains("/account") -> MockFiles.USER_ACCOUNT

            path.contains("/movie/popular")
                    or path.contains("/movie/top_rated")
                    or path.contains("/movie/upcoming")
                    or path.contains("/movie/now_playing") -> MockFiles.MOVIE_LIST

            path.contains("/tv/airing_today")
                    or path.contains("/tv/on_the_air")
                    or path.contains("/tv/popular")
                    or path.contains("/tv/top_rated") -> MockFiles.TV_SHOW_LIST

            path.contains("/movie/$movieId") -> MockFiles.MOVIE_DETAIL
            path.contains("/movie/$movieId/credits") -> MockFiles.MOVIE_CREDITS
            path.contains("/movie/$movieId/videos") -> MockFiles.MOVIE_VIDEOS
            path.contains("/movie/$movieId/images") -> MockFiles.MOVIE_IMAGES
            path.contains("/tv/$tvShowId") -> MockFiles.TV_SHOW_DETAIL
            path.contains("/tv/$tvShowId/credits") -> MockFiles.TV_SHOW_CREDITS
            path.contains("/tv/$tvShowId/videos") -> MockFiles.TV_SHOW_VIDEOS
            path.contains("/tv/$tvShowId/images") -> MockFiles.TV_SHOW_IMAGES

            else -> MockFiles.REMOTE_ERROR
        }

        return buildResponse(filename)
    }

    private fun buildResponse(filename: String): MockResponse = try {
        Reader.fetchJson(filename).let(MockResponse()::setBody)
    } catch (e: Exception) {
        Reader.fetchJson(MockFiles.REMOTE_ERROR).let(MockResponse()::setBody)
    }

}