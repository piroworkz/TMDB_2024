package com.davidluna.architectcoders2024.fakes

import com.davidluna.architectcoders2024.core_domain.data.datastore.LocalPreferencesDataRepository
import com.davidluna.architectcoders2024.core_domain.data.datastore.PreferencesDataSource
import com.davidluna.architectcoders2024.core_domain.usecases.datastore.GetContentKindUseCase
import com.davidluna.architectcoders2024.core_domain.usecases.datastore.PreferencesRepository
import com.davidluna.architectcoders2024.videos_framework.data.remote.datasource.VideosRemoteApi
import com.davidluna.architectcoders2024.media_framework.data.remote.datasources.MediaCatalogRemoteApi
import com.davidluna.architectcoders2024.media_framework.data.remote.datasources.MediaDetailsRemoteApi
import com.davidluna.architectcoders2024.media_domain.data.MediaCatalogDataRepository
import com.davidluna.architectcoders2024.media_domain.data.MediaCatalogRemoteDatasource
import com.davidluna.architectcoders2024.media_domain.data.MovieDetailsDataRepository
import com.davidluna.architectcoders2024.media_domain.data.MovieDetailsDataSource
import com.davidluna.architectcoders2024.media_domain.usecases.FormatDateUseCase
import com.davidluna.architectcoders2024.media_domain.usecases.GetMediaCastUseCase
import com.davidluna.architectcoders2024.media_domain.usecases.GetMediaCatalogUseCase
import com.davidluna.architectcoders2024.media_domain.usecases.GetMediaDetailsUseCase
import com.davidluna.architectcoders2024.media_domain.usecases.GetMediaImagesUseCase
import com.davidluna.architectcoders2024.media_domain.usecases.MovieDetailsRepository
import com.davidluna.architectcoders2024.test_shared.framework.FakeLocalPreferencesDataSource
import com.davidluna.architectcoders2024.videos_domain.data.VideosDataRepository
import com.davidluna.architectcoders2024.videos_domain.data.VideosDataSource
import com.davidluna.architectcoders2024.videos_domain.usecases.GetVideosUseCase
import com.davidluna.architectcoders2024.videos_domain.usecases.VideosRepository

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

    private val local: PreferencesDataSource by lazy {
        FakeLocalPreferencesDataSource()
    }
    private val preferencesRepository: PreferencesRepository by lazy {
        LocalPreferencesDataRepository(
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