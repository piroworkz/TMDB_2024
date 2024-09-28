package com.davidluna.tmdb.core_framework.data.remote

import com.davidluna.tmdb.core_domain.usecases.datastore.SessionUseCase
import com.davidluna.tmdb.core_domain.usecases.location.GetCountryCodeUseCase
import com.davidluna.tmdb.core_framework.di.qualifiers.ApiKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class MoviesInterceptor @Inject constructor(
    @ApiKey private val apiKey: String,
    private val sessionUseCase: SessionUseCase,
    private val getCountryCodeUseCase: GetCountryCodeUseCase,
    private val scope: CoroutineScope
) : Interceptor {

    private var id = String()
    private var region = ""

    init {
        collectAuth()
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
//        addQueryParameter(LIMIT, PAGE_SIZE.toString())
        if (id.isNotEmpty()) {
            addQueryParameter(SESSION_ID_NAME, id)
            if (region.isNotEmpty()) {
                addQueryParameter(REGION, region)
            }
        }
        addQueryParameter(API_KEY_NAME, apiKey)
    }.build()


    private fun collectAuth() {
        scope.launch {
            sessionUseCase().collect { session: com.davidluna.tmdb.core_domain.entities.Session ->
                id = session.id.ifEmpty { session.guestSession.id }
                setRegion()
            }
        }
    }

    private fun setRegion() {
        scope.launch {
            region = getCountryCodeUseCase()
        }
    }

    companion object {
        private const val API_KEY_NAME = "api_key"
        private const val SESSION_ID_NAME = "session_id"
        private const val AUTHORIZATION = "Authorization"
        private const val REGION = "region"
        private const val LIMIT = "limit"
    }

}