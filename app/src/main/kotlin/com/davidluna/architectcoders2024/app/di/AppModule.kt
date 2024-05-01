package com.davidluna.architectcoders2024.app.di

import android.app.Application
import android.location.Geocoder
import androidx.datastore.core.DataStore
import androidx.datastore.core.MultiProcessDataStoreFactory
import com.davidluna.architectcoders2024.app.data.local.datastore.ProtoPreferencesSerializer
import com.davidluna.protodatastore.ProtoPreferences
import com.google.android.gms.location.LocationServices
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
object AppModule {

    @Singleton
    @Provides
    fun provideCoroutineScope(): CoroutineScope =
        CoroutineScope(SupervisorJob() + Dispatchers.IO)

    @Singleton
    @Provides
    fun provideDataStore(application: Application): DataStore<ProtoPreferences> =
        MultiProcessDataStoreFactory.create(
            serializer = ProtoPreferencesSerializer,
            produceFile = { application.filesDir.resolve("session.preferences_pb") }
        )

    @Singleton
    @Provides
    fun provideFusedLocationProviderClient(application: Application) =
        LocationServices.getFusedLocationProviderClient(application)

    @Singleton
    @Provides
    fun provideGeoCoder(application: Application) =
        Geocoder(application)

}