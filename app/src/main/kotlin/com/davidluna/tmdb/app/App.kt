package com.davidluna.tmdb.app

import android.app.Application
import com.davidluna.tmdb.core_framework.data.remote.messaging.InstallNotificationChannels
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {
    @Inject
    lateinit var installNotificationChannels: InstallNotificationChannels

    override fun onCreate() {
        super.onCreate()
        installNotificationChannels()
    }
}