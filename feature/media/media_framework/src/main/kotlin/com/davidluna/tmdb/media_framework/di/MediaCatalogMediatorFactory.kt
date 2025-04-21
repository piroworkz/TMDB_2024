package com.davidluna.tmdb.media_framework.di

import com.davidluna.tmdb.media_framework.data.paging.MediaCatalogRemoteMediator
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory

@AssistedFactory
interface MediaCatalogMediatorFactory {
    fun create(@Assisted("path")path: String, @Assisted("catalogName")catalogName: String): MediaCatalogRemoteMediator
}