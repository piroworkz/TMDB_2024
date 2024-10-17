package com.davidluna.tmdb.fakes

import com.davidluna.tmdb.core_data.framework.local.proto_datastore.PreferencesDataSource
import com.davidluna.tmdb.core_data.repositories.LocalPreferencesDataRepository
import com.davidluna.tmdb.core_domain.repositories.PreferencesRepository
import com.davidluna.tmdb.core_domain.usecases.GetContentKindUseCase
import com.davidluna.tmdb.media_data.framework.remote.datasources.MediaCatalogRemoteDatasource
import com.davidluna.tmdb.media_data.framework.remote.datasources.MediaDetailsRemoteDatasource
import com.davidluna.tmdb.media_data.framework.remote.datasources.VideosRemoteDataSource
import com.davidluna.tmdb.media_data.repositories.MediaCatalogDataRepository
import com.davidluna.tmdb.media_data.repositories.MovieDetailsDataRepository
import com.davidluna.tmdb.media_data.repositories.VideoPlayerDataRepository
import com.davidluna.tmdb.media_domain.repositories.MovieDetailsRepository
import com.davidluna.tmdb.media_domain.repositories.VideosPlayerRepository
import com.davidluna.tmdb.media_domain.usecases.FormatDateUseCase
import com.davidluna.tmdb.media_domain.usecases.GetMediaCastUseCase
import com.davidluna.tmdb.media_domain.usecases.GetMediaCatalogUseCase
import com.davidluna.tmdb.media_domain.usecases.GetMediaDetailsUseCase
import com.davidluna.tmdb.media_domain.usecases.GetMediaImagesUseCase
import com.davidluna.tmdb.media_domain.usecases.GetVideosUseCase

class FakeMediaDI {

    private val videosDataSource: VideosRemoteDataSource by lazy {
        FakeVideosService()
    }

    private val videosPlayerRepository: VideosPlayerRepository by lazy {
        VideoPlayerDataRepository(videosDataSource)
    }

    private val movieDetailsDataSource: MediaDetailsRemoteDatasource by lazy {
        FakeMediaDetailService()
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
        FakeMediaCatalogService()
    }

    private val repository by lazy {
        MediaCatalogDataRepository(mediaCatalogRemoteDatasource)
    }

    private val formatReleaseDateUseCase by lazy {
        FormatDateUseCase()
    }

    val getVideosUseCase by lazy {
        GetVideosUseCase(videosPlayerRepository)
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