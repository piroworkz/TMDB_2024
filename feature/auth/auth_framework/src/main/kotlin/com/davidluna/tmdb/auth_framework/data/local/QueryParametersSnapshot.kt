package com.davidluna.tmdb.auth_framework.data.local

import com.davidluna.tmdb.auth_domain.usecases.GetSessionUseCase
import com.davidluna.tmdb.core_domain.usecases.GetCountryCodeUseCase
import com.davidluna.tmdb.core_framework.data.remote.interceptors.ParametersSnapshot
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QueryParametersSnapshot @Inject constructor(
    getSessionUseCase: GetSessionUseCase,
    getCountryCode: GetCountryCodeUseCase,
    scope: CoroutineScope,
    ioDispatcher: CoroutineDispatcher,
) : ParametersSnapshot {

    private val queryParameters: MutableMap<String, String> = mutableMapOf()

    init {
        scope.launch(ioDispatcher) {
            getSessionUseCase.flow.collect {
                it?.let { queryParameters["session_id"] = it.sessionId }
            }
        }
        scope.launch(ioDispatcher) { getCountryCode().collect { queryParameters["region"] = it } }
    }

    override fun invoke(): Map<String, String> = queryParameters
}