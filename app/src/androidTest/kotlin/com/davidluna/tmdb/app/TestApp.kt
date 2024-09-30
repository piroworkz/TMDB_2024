package com.davidluna.tmdb.app

import android.app.Application
import com.davidluna.tmdb.app.di.configureKoin

class TestApp : Application() {
    override fun onCreate() {
        super.onCreate()
        configureKoin()
    }
}

