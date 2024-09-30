package com.davidluna.tmdb.core_framework.di

import com.davidluna.tmdb.core_domain.data.datastore.PreferencesDataSource
import com.davidluna.tmdb.core_framework.data.local.datastore.LocalPreferencesDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val preferencesModule = module {
    singleOf(::LocalPreferencesDataSource) bind PreferencesDataSource::class
}