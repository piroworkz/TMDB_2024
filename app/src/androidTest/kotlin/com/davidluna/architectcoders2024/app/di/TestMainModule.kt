package com.davidluna.architectcoders2024.app.di

import android.app.Application
import android.location.Geocoder
import androidx.datastore.core.DataStore
import androidx.datastore.core.MultiProcessDataStoreFactory
import com.davidluna.architectcoders2024.core_framework.data.local.datastore.ProtoPreferencesSerializer
import com.davidluna.architectcoders2024.core_framework.data.remote.MoviesInterceptor
import com.davidluna.architectcoders2024.core_framework.data.remote.call_adapter.NetworkCallAdapterFactory
import com.davidluna.architectcoders2024.core_framework.di.CoreModule
import com.davidluna.architectcoders2024.core_framework.di.qualifiers.ApiKey
import com.davidluna.architectcoders2024.core_framework.di.qualifiers.BaseUrl
import com.davidluna.protodatastore.ProtoPreferences
import com.google.android.gms.location.LocationServices
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [MainModule::class, CoreModule::class]
)
object TestMainModule {

    @Singleton
    @Provides
    @ApiKey
    fun provideApiKey(): String = "12345678"

    @Singleton
    @Provides
    @BaseUrl
    fun provideBaseUrl(): String = "http://localhost:8080/v1/public/"

    @Singleton
    @Provides
    fun provideClient(
        interceptor: MoviesInterceptor,
    ): OkHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.NONE
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(this)
            .build()
    }

    @Singleton
    @Provides
    fun provideJsonConverter(): Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        prettyPrint = true
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        @BaseUrl
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

    @Singleton
    @Provides
    fun provideDataStore(application: Application): DataStore<ProtoPreferences> =
        MultiProcessDataStoreFactory.create(
            serializer = ProtoPreferencesSerializer,
            produceFile = { application.filesDir.resolve("test_session.preferences_pb") }
        )

    @Singleton
    @Provides
    fun provideCoroutineScope(): CoroutineScope =
        CoroutineScope(SupervisorJob() + Dispatchers.IO)

    @Singleton
    @Provides
    fun provideFusedLocationProviderClient(application: Application) =
        LocationServices.getFusedLocationProviderClient(application)

    @Singleton
    @Provides
    fun provideGeoCoder(application: Application) =
        Geocoder(application)

}