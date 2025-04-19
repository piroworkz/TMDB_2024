package com.davidluna.tmdb.core_framework.di

import com.davidluna.tmdb.core_domain.data.datastore.LocalPreferencesDataRepository
import com.davidluna.tmdb.core_domain.data.location.RegionDataRepository
import com.davidluna.tmdb.core_domain.data.location.RegionDataSource
import com.davidluna.tmdb.core_domain.usecases.datastore.PreferencesRepository
import com.davidluna.tmdb.core_domain.usecases.location.RegionRepository
import com.davidluna.tmdb.core_framework.data.local.LocationDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CoreDataModule {

    @Binds
    abstract fun bindLocationDataSource(datasource: LocationDataSource): com.davidluna.tmdb.core_domain.data.location.RegionDataSource

    @Binds
    abstract fun bindRegionRepository(repository: com.davidluna.tmdb.core_domain.data.location.RegionDataRepository): RegionRepository

    @Binds
    abstract fun bindLocalSessionRepository(repository: com.davidluna.tmdb.core_domain.data.datastore.LocalPreferencesDataRepository): PreferencesRepository

}

