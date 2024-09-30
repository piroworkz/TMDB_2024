package com.davidluna.tmdb.app.di

import org.koin.core.qualifier.named
import org.koin.dsl.module

object NativeModule {

    init {
        System.loadLibrary("native")
    }

    val apiKeyQualifier = named("apiKey")
    val baseUrlQualifier = named("baseUrl")

    val nativeModule: org.koin.core.module.Module = module {
        single(apiKeyQualifier) { getApiKey() }
        single(baseUrlQualifier) { getBaseUrl() }
    }

    private external fun getApiKey(): String
    private external fun getBaseUrl(): String

}