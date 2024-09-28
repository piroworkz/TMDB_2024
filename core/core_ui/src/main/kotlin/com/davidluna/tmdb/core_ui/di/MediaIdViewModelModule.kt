package com.davidluna.tmdb.core_ui.di

import androidx.lifecycle.SavedStateHandle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class MediaIdViewModelModule {

    @Provides
    @ViewModelScoped
    @MediaId
    fun provideMediaId(savedStateHandle: SavedStateHandle): Int =
        savedStateHandle.get<Int>("mediaId") ?: 0
}