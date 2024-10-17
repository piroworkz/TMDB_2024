package com.davidluna.tmdb.app.di

import com.davidluna.tmdb.app.di.NativeModule.nativeTestModule
import com.davidluna.tmdb.app.framework.TestLocalPreferencesDatasource
import com.davidluna.tmdb.auth_domain.di.authDomainModule
import com.davidluna.tmdb.auth_data.di.authFrameworkModule
import com.davidluna.tmdb.auth_ui.di.authViewModelModule
import com.davidluna.tmdb.core_domain.data.datastore.PreferencesDataSource
import com.davidluna.tmdb.core_domain.di.coreDomainModule
import com.davidluna.tmdb.core_data.di.coreDataModule
import com.davidluna.tmdb.core_data.di.coreModule
import com.davidluna.tmdb.media_domain.di.mediaDomainModule
import com.davidluna.tmdb.media_data.di.mediaFrameworkModule
import com.davidluna.tmdb.media_ui.di.mediaViewModelModule
import com.davidluna.tmdb.auth_ui.di.splashViewModelModule
import com.davidluna.tmdb.videos_domain.di.videosDomainModule
import com.davidluna.tmdb.videos_framework.di.videosFrameworkModule
import com.davidluna.tmdb.videos_ui.di.videosViewModelModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val testModules = listOf(
    nativeTestModule,
    mainAppModule,
    coreModule,
    coreDataModule,
    coreDomainModule,
    getTestDataStoreModule(),
    getTestPreferencesModule(),
    splashViewModelModule,
    authDomainModule,
    authFrameworkModule,
    authViewModelModule,
    mediaDomainModule,
    mediaFrameworkModule,
    mediaViewModelModule,
    videosDomainModule,
    videosFrameworkModule,
    videosViewModelModule
)

private fun getTestDataStoreModule() = module {
    singleOf(::provideTestCoroutineScope)
}

private fun getTestPreferencesModule() = module {
    singleOf(::TestLocalPreferencesDatasource) bind PreferencesDataSource::class
}

private fun provideTestCoroutineScope(): CoroutineScope =
    TestScope(StandardTestDispatcher())