package com.davidluna.tmdb.app.di

import android.app.Application
import com.davidluna.tmdb.app.di.NativeModule.nativeModule
import com.davidluna.tmdb.auth_ui.di.authViewModelModule
import com.davidluna.tmdb.media_ui.di.mediaViewModelModule
import com.davidluna.tmdb2024.application.di.applicationKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

fun Application.configureKoin() {
    startKoin {
        androidContext(this@configureKoin)
        modules(
            nativeModule,
            mainAppModule,
            applicationKoinModule,
            authViewModelModule,
            mediaViewModelModule,
        )
    }
}

