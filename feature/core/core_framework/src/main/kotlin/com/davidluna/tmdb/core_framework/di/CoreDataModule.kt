package com.davidluna.tmdb.core_framework.di

import com.davidluna.tmdb.core_domain.usecases.GetCountryCodeUseCase
import com.davidluna.tmdb.core_domain.usecases.PermissionValidator
import com.davidluna.tmdb.core_framework.data.local.sources.AndroidLocationProvider
import com.davidluna.tmdb.core_framework.data.local.sources.AndroidLocationService
import com.davidluna.tmdb.core_framework.data.local.sources.CountryCodeResolver
import com.davidluna.tmdb.core_framework.data.local.sources.GeoCountryCodeResolver
import com.davidluna.tmdb.core_framework.data.local.sources.LocationPermissionValidator
import com.davidluna.tmdb.core_framework.data.local.sources.LocationService
import com.davidluna.tmdb.core_framework.data.remote.messaging.InstallNotificationChannels
import com.davidluna.tmdb.core_framework.data.remote.messaging.NotificationChannelInstaller
import com.davidluna.tmdb.core_framework.data.remote.messaging.NotificationsManager
import com.davidluna.tmdb.core_framework.data.remote.messaging.TmdbNotificationsManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CoreDataModule {
    @Binds abstract fun bindCountryCodeResolver(source: GeoCountryCodeResolver): CountryCodeResolver
    @Binds abstract fun bindGetCountryCode(source: AndroidLocationProvider): GetCountryCodeUseCase
    @Binds abstract fun bindLocationService(source: AndroidLocationService): LocationService
    @Binds abstract fun bindPermissionValidator(source: LocationPermissionValidator): PermissionValidator
    @Binds abstract fun bindNotificationsManager(source: TmdbNotificationsManager): NotificationsManager
    @Binds abstract fun bindInstallNotificationChannels(source: NotificationChannelInstaller): InstallNotificationChannels
}