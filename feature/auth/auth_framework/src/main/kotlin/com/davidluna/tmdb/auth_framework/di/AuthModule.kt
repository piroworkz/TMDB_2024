package com.davidluna.tmdb.auth_framework.di

import com.davidluna.tmdb.auth_framework.data.remote.RemoteAuthenticationService
import com.davidluna.tmdb.auth_framework.data.remote.UserAccountService
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
    fun provideAuthenticationService(retrofit: Retrofit): RemoteAuthenticationService =
        retrofit.create(RemoteAuthenticationService::class.java)

    @Singleton
    @Provides
    fun provideUserAccountService(retrofit: Retrofit): UserAccountService =
        retrofit.create(UserAccountService::class.java)
}