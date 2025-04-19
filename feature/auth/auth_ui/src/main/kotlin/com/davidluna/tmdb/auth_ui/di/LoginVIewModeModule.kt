package com.davidluna.tmdb.auth_ui.di

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.davidluna.tmdb.core_ui.navigation.destination.AuthNavigation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class LoginVIewModeModule {

    @Provides
    @ViewModelScoped
    fun provideLoginViewModelArguments(savedStateHandle: SavedStateHandle): AuthNavigation.Login =
        savedStateHandle.toRoute<AuthNavigation.Login>()

}