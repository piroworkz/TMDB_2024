package com.davidluna.tmdb.app

import android.app.Application
import com.davidluna.tmdb.app.di.configureKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        configureKoin()
    }

}