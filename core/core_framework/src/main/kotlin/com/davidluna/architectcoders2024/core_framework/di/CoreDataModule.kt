package com.davidluna.architectcoders2024.core_framework.di

import com.davidluna.architectcoders2024.core_domain.data.datastore.LocalPreferencesDataRepository
import com.davidluna.architectcoders2024.core_domain.data.location.RegionDataRepository
import com.davidluna.architectcoders2024.core_domain.data.location.RegionDataSource
import com.davidluna.architectcoders2024.core_domain.usecases.datastore.PreferencesRepository
import com.davidluna.architectcoders2024.core_domain.usecases.location.RegionRepository
import com.davidluna.architectcoders2024.core_framework.data.local.LocationDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CoreDataModule {

    @Binds
    abstract fun bindLocationDataSource(datasource: LocationDataSource): RegionDataSource

    @Binds
    abstract fun bindRegionRepository(repository: RegionDataRepository): RegionRepository

    @Binds
    abstract fun bindLocalSessionRepository(repository: LocalPreferencesDataRepository): PreferencesRepository

}

