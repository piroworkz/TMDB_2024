package com.davidluna.tmdb.app.di

import com.davidluna.tmdb.app.di.NativeModule.apiKeyQualifier
import com.davidluna.tmdb.app.di.NativeModule.baseUrlQualifier
import com.davidluna.tmdb.app.main_ui.presenter.MainViewModel
import com.davidluna.tmdb.core_data.framework.remote.client.KtorClient
import io.ktor.client.HttpClient
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.scope.Scope
import org.koin.dsl.module

val mainAppModule = module {
    single { provideKtorClient() }
    viewModelOf(::MainViewModel)
}

private fun Scope.provideKtorClient(): HttpClient = KtorClient(
    baseUrl = get(baseUrlQualifier),
    apiKey = get(apiKeyQualifier),
    scope = get(),
    sessionFlowUseCase = get(),
    getCountryCodeUseCase = get()
).instance