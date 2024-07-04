package com.davidluna.architectcoders2024.di.data.framework.local

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.core.MultiProcessDataStoreFactory
import com.davidluna.architectcoders2024.core_data_framework.local.datastore.ProtoPreferencesSerializer
import com.davidluna.protodatastore.ProtoPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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


}