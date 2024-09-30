package com.davidluna.tmdb.core_framework.di

import com.davidluna.tmdb.core_domain.data.datastore.LocalPreferencesDataRepository
import com.davidluna.tmdb.core_domain.data.location.RegionDataRepository
import com.davidluna.tmdb.core_domain.data.location.RegionDataSource
import com.davidluna.tmdb.core_domain.repositories.PreferencesRepository
import com.davidluna.tmdb.core_domain.repositories.RegionRepository
import com.davidluna.tmdb.core_framework.data.local.LocationDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module {
    singleOf(::LocationDataSource) bind RegionDataSource::class
    singleOf(::RegionDataRepository) bind RegionRepository::class
    singleOf(::LocalPreferencesDataRepository) bind PreferencesRepository::class
}
