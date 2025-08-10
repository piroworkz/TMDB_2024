package com.davidluna.tmdb.media_framework.di

import com.davidluna.tmdb.media_domain.usecases.GetMediaDetailsUseCase
import com.davidluna.tmdb.media_domain.usecases.GetMediaVideosUseCase
import com.davidluna.tmdb.media_domain.usecases.GetSelectedMediaCatalog
import com.davidluna.tmdb.media_domain.usecases.ObserveMediaCatalogUseCase
import com.davidluna.tmdb.media_domain.usecases.UpdateSelectedEndpoint
import com.davidluna.tmdb.media_framework.data.local.storage.SelectedCatalogDataSource
import com.davidluna.tmdb.media_framework.data.paging.CachePolicyValidator
import com.davidluna.tmdb.media_framework.data.paging.IsCacheExpired
import com.davidluna.tmdb.media_framework.data.repositories.MediaCatalogRepository
import com.davidluna.tmdb.media_framework.data.repositories.MediaDetailsCacheRepository
import com.davidluna.tmdb.media_framework.data.repositories.MediaVideosRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MediaDataModule {
    @Binds
    abstract fun bindGetMediaCatalogUseCase(source: MediaCatalogRepository): ObserveMediaCatalogUseCase
    @Binds
    abstract fun bindIsCacheExpired(source: CachePolicyValidator): IsCacheExpired
    @Binds
    abstract fun bindGetMediaDetailsUseCase(source: MediaDetailsCacheRepository): GetMediaDetailsUseCase
    @Binds
    abstract fun bindGetMediaVideosUseCase(source: MediaVideosRepository): GetMediaVideosUseCase
    @Binds
    abstract fun bindGetContentKindUseCase(source: SelectedCatalogDataSource): GetSelectedMediaCatalog
    @Binds
    abstract fun bindUpdateContentKindUseCase(source: SelectedCatalogDataSource): UpdateSelectedEndpoint
}