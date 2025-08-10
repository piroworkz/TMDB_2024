package com.davidluna.tmdb.media_ui.di

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.davidluna.tmdb.media_ui.navigation.MediaNavigation
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
    @DetailsMediaId
    fun provideMediaId(savedStateHandle: SavedStateHandle): Int =
        savedStateHandle.toRoute<MediaNavigation.Detail>().mediaId

    @Provides
    @ViewModelScoped
    @VideosMediaId
    fun provideVideosMediaId(savedStateHandle: SavedStateHandle): Int =
        savedStateHandle.toRoute<MediaNavigation.Video>().mediaId
}