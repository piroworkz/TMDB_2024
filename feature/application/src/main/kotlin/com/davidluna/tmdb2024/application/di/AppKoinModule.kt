package com.davidluna.tmdb2024.application.di

import android.app.Application
import android.location.Geocoder
import androidx.datastore.core.DataStore
import androidx.datastore.core.MultiProcessDataStoreFactory
import com.davidluna.protodatastore.ProtoPreferences
import com.davidluna.tmdb.auth_domain.data.SessionDataRepository
import com.davidluna.tmdb.auth_domain.data.SessionDataSource
import com.davidluna.tmdb.auth_domain.usecases.CreateGuestSessionIdUseCase
import com.davidluna.tmdb.auth_domain.usecases.CreateRequestTokenUseCase
import com.davidluna.tmdb.auth_domain.usecases.CreateSessionUseCase
import com.davidluna.tmdb.auth_domain.usecases.GetUserAccountUseCase
import com.davidluna.tmdb.auth_domain.usecases.GuestSessionNotExpiredUseCase
import com.davidluna.tmdb.auth_domain.usecases.LoginViewModelUseCases
import com.davidluna.tmdb.auth_domain.usecases.SessionRepository
import com.davidluna.tmdb.auth_framework.data.remote.RemoteSessionDataSource
import com.davidluna.tmdb.core_domain.data.datastore.LocalPreferencesDataRepository
import com.davidluna.tmdb.core_domain.data.datastore.PreferencesDataSource
import com.davidluna.tmdb.core_domain.data.location.RegionDataRepository
import com.davidluna.tmdb.core_domain.data.location.RegionDataSource
import com.davidluna.tmdb.core_domain.repositories.PreferencesRepository
import com.davidluna.tmdb.core_domain.repositories.RegionRepository
import com.davidluna.tmdb.core_domain.usecases.CloseSessionUseCase
import com.davidluna.tmdb.core_domain.usecases.GetContentKindUseCase
import com.davidluna.tmdb.core_domain.usecases.GetCountryCodeUseCase
import com.davidluna.tmdb.core_domain.usecases.SaveContentKindUseCase
import com.davidluna.tmdb.core_domain.usecases.SessionFlowUseCase
import com.davidluna.tmdb.core_domain.usecases.UserAccountUseCase
import com.davidluna.tmdb.core_framework.data.local.LocationDataSource
import com.davidluna.tmdb.core_framework.data.local.datastore.LocalPreferencesDataSource
import com.davidluna.tmdb.core_framework.data.local.datastore.ProtoPreferencesSerializer
import com.davidluna.tmdb.media_domain.data.MediaCatalogDataRepository
import com.davidluna.tmdb.media_domain.data.MediaCatalogRemoteDatasource
import com.davidluna.tmdb.media_domain.data.MovieDetailsDataRepository
import com.davidluna.tmdb.media_domain.data.MovieDetailsDataSource
import com.davidluna.tmdb.media_domain.repositories.MediaRepository
import com.davidluna.tmdb.media_domain.repositories.MovieDetailsRepository
import com.davidluna.tmdb.media_domain.usecases.FormatDateUseCase
import com.davidluna.tmdb.media_domain.usecases.GetMediaCastUseCase
import com.davidluna.tmdb.media_domain.usecases.GetMediaCatalogUseCase
import com.davidluna.tmdb.media_domain.usecases.GetMediaDetailsUseCase
import com.davidluna.tmdb.media_domain.usecases.GetMediaImagesUseCase
import com.davidluna.tmdb.media_framework.data.remote.datasources.MediaCatalogRemoteApi
import com.davidluna.tmdb.media_framework.data.remote.datasources.MediaDetailsRemoteApi
import com.davidluna.tmdb.videos_domain.data.VideosDataRepository
import com.davidluna.tmdb.videos_domain.data.VideosDataSource
import com.davidluna.tmdb.videos_domain.usecases.GetVideosUseCase
import com.davidluna.tmdb.videos_domain.usecases.VideosRepository
import com.davidluna.tmdb.videos_framework.data.remote.datasource.VideosRemoteApi
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val applicationKoinModule = module {
//    singletons
    singleOf(::provideFusedLocationProviderClient)
    singleOf(::provideGeoCoder)
    singleOf(::provideDataStore)
    singleOf(::provideCoroutineScope)
//    Datasources and repositories
    factoryOf(::LocationDataSource) bind RegionDataSource::class
    factoryOf(::RegionDataRepository) bind RegionRepository::class
    factoryOf(::LocalPreferencesDataRepository) bind PreferencesRepository::class
    factoryOf(::LocalPreferencesDataSource) bind PreferencesDataSource::class
    factoryOf(::LocalPreferencesDataRepository) bind PreferencesRepository::class
    factoryOf(::RegionDataRepository) bind RegionRepository::class
    factoryOf(::RemoteSessionDataSource) bind SessionDataSource::class
    factoryOf(::SessionDataRepository) bind SessionRepository::class
    factoryOf(::MediaDetailsRemoteApi) bind MovieDetailsDataSource::class
    factoryOf(::MediaCatalogRemoteApi) bind MediaCatalogRemoteDatasource::class
    factoryOf(::MediaCatalogDataRepository) bind MediaRepository::class
    factoryOf(::MovieDetailsDataRepository) bind MovieDetailsRepository::class
    factoryOf(::VideosRemoteApi) bind VideosDataSource::class
    factoryOf(::VideosDataRepository) bind VideosRepository::class
//    Use cases
    factoryOf(::CloseSessionUseCase)
    factoryOf(::GetContentKindUseCase)
    factoryOf(::GetCountryCodeUseCase)
    factoryOf(::SaveContentKindUseCase)
    factoryOf(::SessionFlowUseCase)
    factoryOf(::UserAccountUseCase)
    factoryOf(::SessionDataRepository) bind SessionRepository::class
    factoryOf(::CreateGuestSessionIdUseCase)
    factoryOf(::CreateRequestTokenUseCase)
    factoryOf(::CreateSessionUseCase)
    factoryOf(::GetUserAccountUseCase)
    factoryOf(::GuestSessionNotExpiredUseCase)
    factoryOf(::LoginViewModelUseCases)
    factoryOf(::MediaCatalogDataRepository) bind MediaRepository::class
    factoryOf(::MovieDetailsDataRepository) bind MovieDetailsRepository::class
    factoryOf(::FormatDateUseCase)
    factoryOf(::GetMediaCastUseCase)
    factoryOf(::GetMediaCatalogUseCase)
    factoryOf(::GetMediaDetailsUseCase)
    factoryOf(::GetMediaImagesUseCase)
    factoryOf(::VideosDataRepository) bind VideosRepository::class
    factoryOf(::GetVideosUseCase)

}

private fun provideFusedLocationProviderClient(application: Application) =
    LocationServices.getFusedLocationProviderClient(application)

private fun provideGeoCoder(application: Application) =
    Geocoder(application)

private fun provideDataStore(application: Application): DataStore<ProtoPreferences> =
    MultiProcessDataStoreFactory.create(
        serializer = ProtoPreferencesSerializer,
        produceFile = { application.filesDir.resolve("session.preferences_pb") }
    )

private fun provideCoroutineScope(): CoroutineScope =
    CoroutineScope(SupervisorJob() + Dispatchers.IO)