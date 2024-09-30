package com.davidluna.tmdb.core_domain.di

import com.davidluna.tmdb.core_domain.data.datastore.LocalPreferencesDataRepository
import com.davidluna.tmdb.core_domain.data.location.RegionDataRepository
import com.davidluna.tmdb.core_domain.repositories.PreferencesRepository
import com.davidluna.tmdb.core_domain.repositories.RegionRepository
import com.davidluna.tmdb.core_domain.usecases.CloseSessionUseCase
import com.davidluna.tmdb.core_domain.usecases.GetContentKindUseCase
import com.davidluna.tmdb.core_domain.usecases.GetCountryCodeUseCase
import com.davidluna.tmdb.core_domain.usecases.SaveContentKindUseCase
import com.davidluna.tmdb.core_domain.usecases.SessionFlowUseCase
import com.davidluna.tmdb.core_domain.usecases.UserAccountUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDomainModule = module {
    singleOf(::LocalPreferencesDataRepository) bind PreferencesRepository::class
    singleOf(::RegionDataRepository) bind RegionRepository::class
    factoryOf(::CloseSessionUseCase)
    factoryOf(::GetContentKindUseCase)
    factoryOf(::GetCountryCodeUseCase)
    factoryOf(::SaveContentKindUseCase)
    factoryOf(::SessionFlowUseCase)
    factoryOf(::UserAccountUseCase)
}