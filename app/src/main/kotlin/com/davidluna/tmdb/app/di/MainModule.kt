package com.davidluna.tmdb.app.di

import com.davidluna.tmdb.app.main_ui.presenter.MainViewModel
import com.davidluna.tmdb.core_framework.data.remote.MediaInterceptor
import com.davidluna.tmdb.core_framework.data.remote.call_adapter.NetworkCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit

val mainAppModule = module {
    single { MediaInterceptor(get(NativeModule.apiKeyQualifier), get(), get(), get()) }
    singleOf(::provideClient)
    singleOf(::provideJsonConverter)
    singleOf(::NetworkCallAdapterFactory)
    single { provideRetrofit(get(NativeModule.baseUrlQualifier), get(), get(), get()) }
    viewModelOf(::MainViewModel)
}

private fun provideClient(
    interceptor: MediaInterceptor,
): OkHttpClient = HttpLoggingInterceptor().run {
    level = HttpLoggingInterceptor.Level.NONE
    OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .addInterceptor(this)
        .build()
}

private fun provideJsonConverter(): Json = Json {
    ignoreUnknownKeys = true
    isLenient = true
    prettyPrint = true
}

private fun provideRetrofit(
    baseUrl: String,
    client: OkHttpClient,
    adapter: NetworkCallAdapterFactory,
    json: Json,
): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .addCallAdapterFactory(adapter)
        .build()