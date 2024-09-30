package com.davidluna.tmdb.app.di

import androidx.datastore.core.DataStore
import androidx.datastore.core.MultiProcessDataStoreFactory
import androidx.test.platform.app.InstrumentationRegistry
import com.davidluna.protodatastore.ProtoPreferences
import com.davidluna.tmdb.app.di.NativeModule.nativeTestModule
import com.davidluna.tmdb.app.framework.TestLocalPreferencesDatasource
import com.davidluna.tmdb.auth_domain.di.authDomainModule
import com.davidluna.tmdb.auth_framework.di.authFrameworkModule
import com.davidluna.tmdb.auth_ui.di.authViewModelModule
import com.davidluna.tmdb.core_domain.data.datastore.PreferencesDataSource
import com.davidluna.tmdb.core_domain.di.coreDomainModule
import com.davidluna.tmdb.core_framework.data.local.datastore.ProtoPreferencesSerializer
import com.davidluna.tmdb.core_framework.di.coreDataModule
import com.davidluna.tmdb.core_framework.di.coreModule
import com.davidluna.tmdb.media_domain.di.mediaDomainModule
import com.davidluna.tmdb.media_framework.di.mediaFrameworkModule
import com.davidluna.tmdb.media_ui.di.mediaViewModelModule
import com.davidluna.tmdb.splash.di.splashViewModelModule
import com.davidluna.tmdb.videos_domain.di.videosDomainModule
import com.davidluna.tmdb.videos_framework.di.videosFrameworkModule
import com.davidluna.tmdb.videos_ui.di.videosViewModelModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

private val testDataStoreModule = module {
    singleOf(::provideTestCoroutineScope)
}

private val testPreferencesModule = module {
    singleOf(::TestLocalPreferencesDatasource) bind PreferencesDataSource::class
}

val testModules = listOf(
    nativeTestModule,
    mainAppModule,
    coreModule,
    coreDataModule,
    coreDomainModule,
    testDataStoreModule,
    testPreferencesModule,
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


private fun provideProtoDataStore(scope: CoroutineScope): DataStore<ProtoPreferences> =
    MultiProcessDataStoreFactory.create(
        serializer = ProtoPreferencesSerializer,
        scope = scope,
        produceFile = {
            InstrumentationRegistry
                .getInstrumentation()
                .targetContext
                .applicationContext
                .filesDir
                .resolve("test_session.preferences_pb")
        }
    )


private fun provideTestCoroutineScope(): CoroutineScope =
    TestScope(StandardTestDispatcher())
