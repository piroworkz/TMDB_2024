package com.davidluna.tmdb.app.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.MultiProcessDataStoreFactory
import androidx.test.core.app.ApplicationProvider
import com.davidluna.tmdb.app.di.NativeModule
import com.davidluna.tmdb.core_framework.data.local.datastore.ProtoPreferencesSerializer
import com.davidluna.tmdb.core_framework.di.DataStoreModule
import com.davidluna.tmdb.core_framework.di.qualifiers.ApiKey
import com.davidluna.tmdb.core_framework.di.qualifiers.BaseUrl
import com.davidluna.protodatastore.ProtoPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import javax.inject.Singleton

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NativeModule::class, DataStoreModule::class]
)
@Module
object TestModule {

    @Singleton
    @Provides
    @ApiKey
    fun provideApiKey(): String = "fake_api_key"

    @Singleton
    @Provides
    @BaseUrl
    fun provideBaseUrl(): String = "http://localhost:8080/"

    @Singleton
    @Provides
    fun provideTestContext(): Context = ApplicationProvider.getApplicationContext()

    @Singleton
    @Provides
    fun provideCoroutineScope(): CoroutineScope =
        TestScope(SupervisorJob() + StandardTestDispatcher())

    @Singleton
    @Provides
    fun provideDataStore(
        testScope: CoroutineScope,
        testContext: Context,
    ): DataStore<ProtoPreferences> =
        MultiProcessDataStoreFactory.create(
            serializer = ProtoPreferencesSerializer,
            scope = testScope,
            produceFile = {
                testContext
                    .filesDir
                    .resolve("test_session.preferences_pb")
            }
        )
}

