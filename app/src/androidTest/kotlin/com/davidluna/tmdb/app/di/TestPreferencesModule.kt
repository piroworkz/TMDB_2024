package com.davidluna.tmdb.app.di

import com.davidluna.tmdb.app.framework.TestLocalPreferencesDatasource
import com.davidluna.tmdb.core_domain.data.datastore.PreferencesDataSource
import com.davidluna.tmdb.core_framework.di.PreferencesModule
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [PreferencesModule::class]
)
abstract class TestPreferencesModule {

    @Binds
    abstract fun bindLocalStorageDatastore(
        dataStore: TestLocalPreferencesDatasource,
    ): com.davidluna.tmdb.core_domain.data.datastore.PreferencesDataSource
}