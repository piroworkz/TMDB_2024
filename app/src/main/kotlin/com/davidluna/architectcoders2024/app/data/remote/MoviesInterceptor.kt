package com.davidluna.architectcoders2024.app.data.remote

import com.davidluna.architectcoders2024.BuildConfig
import com.davidluna.architectcoders2024.app.data.local.datastore.SessionDatastore
import com.davidluna.architectcoders2024.app.data.local.location.RegionSource
import com.davidluna.protodatastore.AuthenticationValues
import com.davidluna.protodatastore.copy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.util.Locale

class MoviesInterceptor(
    private val session: SessionDatastore,
    private val scope: CoroutineScope,
    private val regionSource: RegionSource
) : Interceptor {
    private var auth = AuthenticationValues.getDefaultInstance()
    private var region = RegionSource.DEFAULT_COUNTRY_CODE

    init {
        collectAuth()
        scope.launch {
            region = regionSource.getCountryCode()
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return try {
            val url = buildUrl(request)
            val newRequest = buildRequest(request, url)
            chain.proceed(newRequest)
        } catch (e: Exception) {
            chain.proceed(request)
        }
    }

    private fun buildRequest(request: Request, url: HttpUrl) = request.newBuilder().apply {
        addHeader(AUTHORIZATION, "$API_KEY_NAME $API_KEY")
        url(url)
    }.build()

    private fun buildUrl(request: Request) = request.url.newBuilder().apply {
        if (auth.sessionId.isNotEmpty()) {
            addQueryParameter(SESSION_ID_NAME, auth.sessionId)
        }
        addQueryParameter(LANGUAGE, Locale.getDefault().toLanguageTag())
        addQueryParameter(REGION, region)
        addQueryParameter(API_KEY_NAME, API_KEY)
    }.build()


    companion object {
        private const val API_KEY = BuildConfig.API_KEY
        private const val API_KEY_NAME = "api_key"
        private const val SESSION_ID_NAME = "session_id"
        private const val AUTHORIZATION = "Authorization"
        private const val LANGUAGE = "language"
        private const val REGION = "region"
    }

    private fun collectAuth() {
        scope.launch {
            session.getAuth().collect {
                auth = auth.copy {
                    sessionId = it.sessionId
                    token = it.token
                }
            }
        }
    }

}