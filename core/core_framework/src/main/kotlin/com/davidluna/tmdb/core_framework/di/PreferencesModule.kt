package com.davidluna.tmdb.core_framework.di

import com.davidluna.tmdb.core_domain.data.datastore.PreferencesDataSource
import com.davidluna.tmdb.core_framework.data.local.datastore.LocalPreferencesDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferencesModule {

    @Binds
    abstract fun bindLocalStorageDatastore(dataStore: LocalPreferencesDataSource): com.davidluna.tmdb.core_domain.data.datastore.PreferencesDataSource

}