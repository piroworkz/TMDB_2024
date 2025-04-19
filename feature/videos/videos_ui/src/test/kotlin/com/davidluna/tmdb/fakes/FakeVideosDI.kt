package com.davidluna.tmdb.fakes

import com.davidluna.tmdb.core_domain.data.datastore.LocalPreferencesDataRepository
import com.davidluna.tmdb.core_domain.data.datastore.PreferencesDataSource
import com.davidluna.tmdb.core_domain.usecases.datastore.GetContentKindUseCase
import com.davidluna.tmdb.core_domain.usecases.datastore.PreferencesRepository
import com.davidluna.tmdb.videos_framework.data.remote.datasource.VideosRemoteApi
import com.davidluna.tmdb.test_shared.framework.FakeLocalPreferencesDataSource
import com.davidluna.tmdb.videos_domain.data.VideosDataRepository
import com.davidluna.tmdb.videos_domain.data.VideosDataSource
import com.davidluna.tmdb.videos_domain.usecases.GetVideosUseCase
import com.davidluna.tmdb.videos_domain.usecases.VideosRepository

class FakeVideosDI {

    private val videosDataSource: VideosDataSource by lazy {
        VideosRemoteApi(FakeVideosServiceImpl())
    }

    private val videosRepository: VideosRepository by lazy {
        VideosDataRepository(videosDataSource)
    }
    private val local: com.davidluna.tmdb.core_domain.data.datastore.PreferencesDataSource by lazy {
        FakeLocalPreferencesDataSource()
    }
    private val preferencesRepository: PreferencesRepository by lazy {
        com.davidluna.tmdb.core_domain.data.datastore.LocalPreferencesDataRepository(
            local = local
        )
    }

    val getVideosUseCase by lazy {
        GetVideosUseCase(videosRepository)
    }

    val getContentKindUseCase by lazy {
        GetContentKindUseCase(preferencesRepository)
    }

}