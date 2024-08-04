package com.davidluna.architectcoders2024.auth_framework.di

import com.davidluna.architectcoders2024.auth_framework.data.remote.SessionService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Singleton
    @Provides
    fun provideAuthenticationService(retrofit: Retrofit): SessionService =
        retrofit.create(SessionService::class.java)
}