package com.davidluna.tmdb.fakes

import com.davidluna.tmdb.core_domain.data.datastore.LocalPreferencesDataRepository
import com.davidluna.tmdb.core_domain.data.datastore.PreferencesDataSource
import com.davidluna.tmdb.core_domain.usecases.datastore.GetContentKindUseCase
import com.davidluna.tmdb.core_domain.usecases.datastore.PreferencesRepository
import com.davidluna.tmdb.videos_framework.data.remote.datasource.VideosRemoteApi
import com.davidluna.tmdb.media_framework.data.remote.datasources.MediaCatalogRemoteApi
import com.davidluna.tmdb.media_framework.data.remote.datasources.MediaDetailsRemoteApi
import com.davidluna.tmdb.media_domain.data.MediaCatalogDataRepository
import com.davidluna.tmdb.media_domain.data.MediaCatalogRemoteDatasource
import com.davidluna.tmdb.media_domain.data.MovieDetailsDataRepository
import com.davidluna.tmdb.media_domain.data.MovieDetailsDataSource
import com.davidluna.tmdb.media_domain.usecases.FormatDateUseCase
import com.davidluna.tmdb.media_domain.usecases.GetMediaCastUseCase
import com.davidluna.tmdb.media_domain.usecases.GetMediaCatalogUseCase
import com.davidluna.tmdb.media_domain.usecases.GetMediaDetailsUseCase
import com.davidluna.tmdb.media_domain.usecases.GetMediaImagesUseCase
import com.davidluna.tmdb.media_domain.usecases.MovieDetailsRepository
import com.davidluna.tmdb.test_shared.framework.FakeLocalPreferencesDataSource
import com.davidluna.tmdb.videos_domain.data.VideosDataRepository
import com.davidluna.tmdb.videos_domain.data.VideosDataSource
import com.davidluna.tmdb.videos_domain.usecases.GetVideosUseCase
import com.davidluna.tmdb.videos_domain.usecases.VideosRepository

class FakeMediaDI {

    private val videosDataSource: VideosDataSource by lazy {
        VideosRemoteApi(FakeVideosServiceImpl())
    }

    private val videosRepository: VideosRepository by lazy {
        VideosDataRepository(videosDataSource)
    }

    private val movieDetailsDataSource: MovieDetailsDataSource by lazy {
        MediaDetailsRemoteApi(FakeMediaDetailServiceImpl())
    }

    private val movieDetailsRepository: MovieDetailsRepository by lazy {
        MovieDetailsDataRepository(movieDetailsDataSource)
    }

    private val local: com.davidluna.tmdb.core_domain.data.datastore.PreferencesDataSource by lazy {
        FakeLocalPreferencesDataSource()
    }
    private val preferencesRepository: PreferencesRepository by lazy {
        com.davidluna.tmdb.core_domain.data.datastore.LocalPreferencesDataRepository(
            local = local
        )
    }

    private val mediaCatalogRemoteDatasource: MediaCatalogRemoteDatasource by lazy {
        MediaCatalogRemoteApi(FakeMediaCatalogServiceImpl())
    }

    private val repository by lazy {
        MediaCatalogDataRepository(mediaCatalogRemoteDatasource)
    }

    private val formatReleaseDateUseCase by lazy {
        FormatDateUseCase()
    }

    private val getVideosUseCase by lazy {
        GetVideosUseCase(videosRepository)
    }

    val getContentKindUseCase by lazy {
        GetContentKindUseCase(preferencesRepository)
    }

    val mediaCatalogUseCase by lazy {
        GetMediaCatalogUseCase(repository)
    }

    val getMediaDetailsUseCase: GetMediaDetailsUseCase by lazy {
        GetMediaDetailsUseCase(
            repository = movieDetailsRepository,
            formatDate = formatReleaseDateUseCase,
            getVideosUseCase = getVideosUseCase
        )
    }

    val getMediaCatalogUseCase: GetMediaCatalogUseCase by lazy {
        GetMediaCatalogUseCase(repository)
    }

    val getMediaCastUseCase: GetMediaCastUseCase by lazy {
        GetMediaCastUseCase(movieDetailsRepository)
    }

    val getMediaImagesUseCase: GetMediaImagesUseCase by lazy {
        GetMediaImagesUseCase(movieDetailsRepository)
    }

}