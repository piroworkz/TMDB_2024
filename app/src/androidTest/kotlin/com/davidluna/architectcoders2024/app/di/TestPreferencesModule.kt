package com.davidluna.architectcoders2024.app.di

import com.davidluna.architectcoders2024.app.framework.TestLocalPreferencesDatasource
import com.davidluna.architectcoders2024.core_domain.data.datastore.PreferencesDataSource
import com.davidluna.architectcoders2024.core_framework.di.PreferencesModule
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
    ): PreferencesDataSource
}