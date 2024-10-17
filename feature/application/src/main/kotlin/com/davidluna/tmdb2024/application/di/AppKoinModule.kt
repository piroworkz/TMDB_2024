package com.davidluna.tmdb2024.application.di

import android.app.Application
import android.location.Geocoder
import androidx.datastore.core.DataStore
import androidx.datastore.core.MultiProcessDataStoreFactory
import com.davidluna.protodatastore.ProtoPreferences
import com.davidluna.tmdb.auth_data.framework.remote.datasources.SessionDatasource
import com.davidluna.tmdb.auth_data.framework.remote.datasources.SessionService
import com.davidluna.tmdb.auth_data.repositories.SessionDataRepository
import com.davidluna.tmdb.auth_domain.repositories.SessionRepository
import com.davidluna.tmdb.auth_domain.usecases.CreateGuestSessionIdUseCase
import com.davidluna.tmdb.auth_domain.usecases.CreateRequestTokenUseCase
import com.davidluna.tmdb.auth_domain.usecases.CreateSessionUseCase
import com.davidluna.tmdb.auth_domain.usecases.GetUserAccountUseCase
import com.davidluna.tmdb.auth_domain.usecases.GuestSessionNotExpiredUseCase
import com.davidluna.tmdb.auth_domain.usecases.LoginViewModelUseCases
import com.davidluna.tmdb.core_data.framework.local.CountryCodeDatasource
import com.davidluna.tmdb.core_data.framework.local.CountryCodeLocalDatasource
import com.davidluna.tmdb.core_data.framework.local.proto_datastore.PreferencesDataSource
import com.davidluna.tmdb.core_data.framework.local.proto_datastore.ProtoPreferencesDatasource
import com.davidluna.tmdb.core_data.framework.local.proto_datastore.ProtoPreferencesSerializer
import com.davidluna.tmdb.core_data.repositories.LocalPreferencesDataRepository
import com.davidluna.tmdb.core_data.repositories.RegionDataRepository
import com.davidluna.tmdb.core_domain.repositories.PreferencesRepository
import com.davidluna.tmdb.core_domain.repositories.RegionRepository
import com.davidluna.tmdb.core_domain.usecases.CloseSessionUseCase
import com.davidluna.tmdb.core_domain.usecases.GetContentKindUseCase
import com.davidluna.tmdb.core_domain.usecases.GetCountryCodeUseCase
import com.davidluna.tmdb.core_domain.usecases.SaveContentKindUseCase
import com.davidluna.tmdb.core_domain.usecases.SessionFlowUseCase
import com.davidluna.tmdb.core_domain.usecases.UserAccountUseCase
import com.davidluna.tmdb.media_data.framework.remote.datasources.MediaCatalogRemoteDatasource
import com.davidluna.tmdb.media_data.framework.remote.datasources.MediaCatalogService
import com.davidluna.tmdb.media_data.framework.remote.datasources.MediaDetailsRemoteDatasource
import com.davidluna.tmdb.media_data.framework.remote.datasources.MediaDetailsService
import com.davidluna.tmdb.media_data.framework.remote.datasources.VideosRemoteDataSource
import com.davidluna.tmdb.media_data.framework.remote.datasources.VideosService
import com.davidluna.tmdb.media_data.repositories.MediaCatalogDataRepository
import com.davidluna.tmdb.media_data.repositories.MovieDetailsDataRepository
import com.davidluna.tmdb.media_data.repositories.VideoPlayerDataRepository
import com.davidluna.tmdb.media_domain.repositories.MediaRepository
import com.davidluna.tmdb.media_domain.repositories.MovieDetailsRepository
import com.davidluna.tmdb.media_domain.repositories.VideosPlayerRepository
import com.davidluna.tmdb.media_domain.usecases.FormatDateUseCase
import com.davidluna.tmdb.media_domain.usecases.GetMediaCastUseCase
import com.davidluna.tmdb.media_domain.usecases.GetMediaCatalogUseCase
import com.davidluna.tmdb.media_domain.usecases.GetMediaDetailsUseCase
import com.davidluna.tmdb.media_domain.usecases.GetMediaImagesUseCase
import com.davidluna.tmdb.media_domain.usecases.GetVideosUseCase
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
    factoryOf(::CountryCodeLocalDatasource) bind CountryCodeDatasource::class
    factoryOf(::RegionDataRepository) bind RegionRepository::class
    factoryOf(::LocalPreferencesDataRepository) bind PreferencesRepository::class
    factoryOf(::ProtoPreferencesDatasource) bind PreferencesDataSource::class
    factoryOf(::LocalPreferencesDataRepository) bind PreferencesRepository::class
    factoryOf(::RegionDataRepository) bind RegionRepository::class
    factoryOf(::SessionService) bind SessionDatasource::class
    factoryOf(::SessionDataRepository) bind SessionRepository::class
    factoryOf(::MediaDetailsService) bind MediaDetailsRemoteDatasource::class
    factoryOf(::MediaCatalogService) bind MediaCatalogRemoteDatasource::class
    factoryOf(::MediaCatalogDataRepository) bind MediaRepository::class
    factoryOf(::MovieDetailsDataRepository) bind MovieDetailsRepository::class
    factoryOf(::VideosService) bind VideosRemoteDataSource::class
    factoryOf(::VideoPlayerDataRepository) bind VideosPlayerRepository::class
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
    factoryOf(::VideoPlayerDataRepository) bind VideosPlayerRepository::class
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