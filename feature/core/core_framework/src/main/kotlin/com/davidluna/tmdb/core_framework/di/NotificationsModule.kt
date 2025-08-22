package com.davidluna.tmdb.core_framework.di

import android.app.Application
import android.app.NotificationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationsModule {
    @Singleton
    @Provides
    fun provideNotificationManager(application: Application): NotificationManager =
        application.getSystemService(NotificationManager::class.java)

}