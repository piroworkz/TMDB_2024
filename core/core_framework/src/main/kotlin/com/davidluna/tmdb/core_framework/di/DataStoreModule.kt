package com.davidluna.tmdb.core_framework.di

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.core.MultiProcessDataStoreFactory
import com.davidluna.tmdb.core_framework.data.local.datastore.ProtoPreferencesSerializer
import com.davidluna.protodatastore.ProtoPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Singleton
    @Provides
    fun provideDataStore(application: Application): DataStore<ProtoPreferences> =
        MultiProcessDataStoreFactory.create(
            serializer = ProtoPreferencesSerializer,
            produceFile = { application.filesDir.resolve("session.preferences_pb") }
        )

    @Singleton
    @Provides
    fun provideCoroutineScope(): CoroutineScope =
        CoroutineScope(SupervisorJob() + Dispatchers.IO)

}