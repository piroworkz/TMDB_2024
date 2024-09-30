package com.davidluna.tmdb.app.di

import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

object NativeModule {

    init {
        System.loadLibrary("native")
    }

    val apiKeyQualifier = named("apiKey")
    val baseUrlQualifier = named("baseUrl")

    val nativeModule: Module = module {
        single(apiKeyQualifier) { getApiKey() }
        single(baseUrlQualifier) { getBaseUrl() }
    }

    val nativeTestModule: Module = module {
        single(apiKeyQualifier) { "test" }
        single(baseUrlQualifier) { "http://localhost:8080" }
    }

    private external fun getApiKey(): String
    private external fun getBaseUrl(): String

}