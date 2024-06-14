package com.davidluna.architectcoders2024.app.data.remote.utils

import com.davidluna.architectcoders2024.app.di.annotations.ApiKey
import com.davidluna.architectcoders2024.usecases.location.GetCountryCodeUseCase
import com.davidluna.architectcoders2024.usecases.preferences.SessionIdUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class MoviesInterceptor @Inject constructor(
    @ApiKey private val apiKey: String,
    private val sessionIdUseCase: SessionIdUseCase,
    private val getCountryCodeUseCase: GetCountryCodeUseCase,
    private val scope: CoroutineScope
) : Interceptor {

    private var id = String()
    private var region = ""

    init {
        collectAuth()
        scope.launch {
            region = getCountryCodeUseCase()
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
        addHeader(AUTHORIZATION, "$API_KEY_NAME $apiKey")
        url(url)
    }.build()

    private fun buildUrl(request: Request) = request.url.newBuilder().apply {
        if (id.isNotEmpty()) {
            addQueryParameter(SESSION_ID_NAME, id)
        }
        if (region.isNotEmpty()) {
            addQueryParameter(REGION, region)
        }
        addQueryParameter(API_KEY_NAME, apiKey)
    }.build()


    companion object {
        private const val API_KEY_NAME = "api_key"
        private const val SESSION_ID_NAME = "session_id"
        private const val AUTHORIZATION = "Authorization"
        private const val REGION = "region"
    }

    private fun collectAuth() {
        scope.launch {
            sessionIdUseCase().collect {
                id = it
            }
        }
    }

}