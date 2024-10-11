package com.davidluna.tmdb.core_framework.data.remote

import com.davidluna.tmdb.core_domain.entities.Session
import com.davidluna.tmdb.core_domain.usecases.GetCountryCodeUseCase
import com.davidluna.tmdb.core_domain.usecases.SessionFlowUseCase
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.android.Android
import io.ktor.client.engine.android.AndroidEngineConfig
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.plugin
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class KtorClient(
    private val baseUrl: String,
    private val apiKey: String,
    private val scope: CoroutineScope,
    private val sessionFlowUseCase: SessionFlowUseCase,
    private val getCountryCodeUseCase: GetCountryCodeUseCase,
) {

    private var id: String = String()
    private var region: String = String()

    init {
        collectAuth()
    }

    val instance: HttpClient = HttpClient(Android) {
        installContentNegotiation()
        configDefaultRequest()
        install(Logging) {
            level = LogLevel.NONE
        }

    }.apply {
        plugin(HttpSend).intercept { request: HttpRequestBuilder ->
            id.ifNotEmpty { request.parameter(SESSION_ID_NAME, it) }
            region.ifNotEmpty { request.parameter(REGION, it) }
            execute(request)
        }
    }

    private fun HttpClientConfig<AndroidEngineConfig>.configDefaultRequest() {
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = baseUrl
                path("3/")
                headers.append("Accept", "application/json")
                headers.append(AUTHORIZATION, "$API_KEY_NAME $apiKey")
                parameters.append(API_KEY_NAME, apiKey)
            }
        }
    }

    private fun HttpClientConfig<AndroidEngineConfig>.installContentNegotiation() {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                prettyPrint = true
            })
        }
    }

    private fun collectAuth() {
        scope.launch {
            sessionFlowUseCase().collect { session: Session ->
                id = session.id.ifEmpty { session.guestSession.id }
                setRegion()
            }
        }
    }

    private fun setRegion() {
        scope.launch { region = getCountryCodeUseCase() }
    }


    private fun String.ifNotEmpty(action: (String) -> Unit) {
        if (isNullOrEmpty().not()) action(this)
    }

    companion object {
        private const val API_KEY_NAME = "api_key"
        private const val SESSION_ID_NAME = "session_id"
        private const val AUTHORIZATION = "Authorization"
        private const val REGION = "region"
    }
}