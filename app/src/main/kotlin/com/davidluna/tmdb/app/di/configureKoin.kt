package com.davidluna.tmdb.app.di

import android.app.Application
import com.davidluna.tmdb.app.di.NativeModule.nativeModule
import com.davidluna.tmdb.auth_domain.di.authDomainModule
import com.davidluna.tmdb.auth_framework.di.authFrameworkModule
import com.davidluna.tmdb.auth_ui.di.authViewModelModule
import com.davidluna.tmdb.core_domain.di.coreDomainModule
import com.davidluna.tmdb.core_framework.di.coreDataModule
import com.davidluna.tmdb.core_framework.di.coreModule
import com.davidluna.tmdb.core_framework.di.dataStoreModule
import com.davidluna.tmdb.core_framework.di.preferencesModule
import com.davidluna.tmdb.media_domain.di.mediaDomainModule
import com.davidluna.tmdb.media_framework.di.mediaFrameworkModule
import com.davidluna.tmdb.media_ui.di.mediaViewModelModule
import com.davidluna.tmdb.videos_domain.di.videosDomainModule
import com.davidluna.tmdb.videos_framework.di.videosFrameworkModule
import com.davidluna.tmdb.videos_ui.di.videosViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

fun Application.configureKoin() {
    startKoin {
        androidContext(this@configureKoin)
        modules(
            nativeModule,
            mainAppModule,
            coreModule,
            coreDataModule,
            coreDomainModule,
            dataStoreModule,
            preferencesModule,
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
    }
}