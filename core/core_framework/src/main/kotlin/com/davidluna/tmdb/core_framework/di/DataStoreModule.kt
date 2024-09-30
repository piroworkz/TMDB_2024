package com.davidluna.tmdb.core_framework.di

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.core.MultiProcessDataStoreFactory
import com.davidluna.protodatastore.ProtoPreferences
import com.davidluna.tmdb.core_framework.data.local.datastore.ProtoPreferencesSerializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module

val dataStoreModule = module {
    single { provideDataStore(get()) }
    single { provideCoroutineScope() }
}

private fun provideDataStore(application: Application): DataStore<ProtoPreferences> =
    MultiProcessDataStoreFactory.create(
        serializer = ProtoPreferencesSerializer,
        produceFile = { application.filesDir.resolve("session.preferences_pb") }
    )

private fun provideCoroutineScope(): CoroutineScope =
    CoroutineScope(SupervisorJob() + Dispatchers.IO)
