package com.davidluna.tmdb.auth_framework.di

import android.app.Application
import androidx.room.Room
import com.davidluna.tmdb.auth_framework.data.local.database.AuthenticationDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomAuthenticationModule {

    @Singleton
    @Provides
    fun provideAuthenticationDatabase(app: Application): AuthenticationDatabase =
        Room.databaseBuilder(app, AuthenticationDatabase::class.java, "authentication.db").build()

    @Singleton
    @Provides
    fun provideAccountDao(db: AuthenticationDatabase) = db.accountDao

    @Singleton
    @Provides
    fun provideSessionDao(db: AuthenticationDatabase) = db.sessionDao
}