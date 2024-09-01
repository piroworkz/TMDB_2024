package com.davidluna.architectcoders2024.core_framework.di

import com.davidluna.architectcoders2024.core_domain.data.datastore.PreferencesDataSource
import com.davidluna.architectcoders2024.core_framework.data.local.datastore.LocalPreferencesDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferencesModule {

    @Binds
    abstract fun bindLocalStorageDatastore(dataStore: LocalPreferencesDataSource): PreferencesDataSource

}