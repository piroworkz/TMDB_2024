package com.davidluna.tmdb.media_framework.di

import com.davidluna.tmdb.media_framework.data.local.database.dao.MediaDao
import com.davidluna.tmdb.media_framework.data.local.database.dao.RemoteKeysDao
import com.davidluna.tmdb.media_framework.data.paging.IsCacheExpired
import com.davidluna.tmdb.media_framework.data.paging.MediaCatalogRemoteMediator
import com.davidluna.tmdb.media_framework.data.remote.services.RemoteMediaService

class MediaCatalogMediatorFactorySpy(
    private val mediaDao: MediaDao,
    private val mediaService: RemoteMediaService,
    private val remoteKeysDao: RemoteKeysDao,
    private val isCacheExpired: IsCacheExpired,
) : MediaCatalogMediatorFactory {
    override fun create(
        path: String,
        catalogName: String,
    ): MediaCatalogRemoteMediator {
        return MediaCatalogRemoteMediator(
            path = path,
            catalogName = catalogName,
            mediaDao = mediaDao,
            mediaService = mediaService,
            remoteKeysDao = remoteKeysDao,
            isCacheExpired = isCacheExpired
        )
    }
}