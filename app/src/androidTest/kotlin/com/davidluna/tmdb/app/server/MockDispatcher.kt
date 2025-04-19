package com.davidluna.tmdb.app.server

import com.davidluna.tmdb.test_shared.reader.Reader
import com.davidluna.tmdb.test_shared.reader.TestConstants.AUTH_GUEST_SESSION
import com.davidluna.tmdb.test_shared.reader.TestConstants.AUTH_SESSION_NEW
import com.davidluna.tmdb.test_shared.reader.TestConstants.AUTH_TOKEN_NEW
import com.davidluna.tmdb.test_shared.reader.TestConstants.MOVIE_CREDITS
import com.davidluna.tmdb.test_shared.reader.TestConstants.MOVIE_DETAIL
import com.davidluna.tmdb.test_shared.reader.TestConstants.MOVIE_IMAGES
import com.davidluna.tmdb.test_shared.reader.TestConstants.MOVIE_LIST
import com.davidluna.tmdb.test_shared.reader.TestConstants.MOVIE_VIDEOS
import com.davidluna.tmdb.test_shared.reader.TestConstants.REMOTE_ERROR
import com.davidluna.tmdb.test_shared.reader.TestConstants.TV_SHOW_CREDITS
import com.davidluna.tmdb.test_shared.reader.TestConstants.TV_SHOW_DETAIL
import com.davidluna.tmdb.test_shared.reader.TestConstants.TV_SHOW_IMAGES
import com.davidluna.tmdb.test_shared.reader.TestConstants.TV_SHOW_LIST
import com.davidluna.tmdb.test_shared.reader.TestConstants.TV_SHOW_VIDEOS
import com.davidluna.tmdb.test_shared.reader.TestConstants.USER_ACCOUNT
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class MockDispatcher : Dispatcher() {

    private val movieId = 1022789
    private val tvShowId = 209374

    override fun dispatch(request: RecordedRequest): MockResponse =
        getResponse(request.path)

    private fun getResponse(path: String?): MockResponse {
        if (path.isNullOrEmpty()) {
            return fromFile(REMOTE_ERROR)
        }

        val fileName = when {
            path.contains("authentication/token/new") -> AUTH_TOKEN_NEW
            path.contains("authentication/session/new") -> AUTH_SESSION_NEW
            path.contains("authentication/guest_session/new") -> AUTH_GUEST_SESSION
            path.contains("account") -> USER_ACCOUNT
            path.contains("movie/popular")
                    or path.contains("movie/top_rated")
                    or path.contains("movie/upcoming")
                    or path.contains("movie/now_playing") -> MOVIE_LIST

            path.contains("tv/airing_today")
                    or path.contains("tv/on_the_air")
                    or path.contains("tv/popular")
                    or path.contains("tv/top_rated") -> TV_SHOW_LIST

            path.contains("movie/$movieId/credits") -> MOVIE_CREDITS
            path.contains("movie/$movieId/images") -> MOVIE_IMAGES
            path.contains("movie/$movieId/videos") -> MOVIE_VIDEOS
            path.contains("movie/$movieId/recommendations") -> MOVIE_LIST
            path.contains("movie/$movieId/similar") -> MOVIE_LIST
            path.contains("movie/$movieId") -> MOVIE_DETAIL
            path.contains("tv/$tvShowId/credits") -> TV_SHOW_CREDITS
            path.contains("tv/$tvShowId/images") -> TV_SHOW_IMAGES
            path.contains("tv/$tvShowId/videos") -> TV_SHOW_VIDEOS
            path.contains("tv/$tvShowId/recommendations") -> TV_SHOW_LIST
            path.contains("tv/$tvShowId/similar") -> TV_SHOW_LIST
            path.contains("tv/$tvShowId") -> TV_SHOW_DETAIL

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