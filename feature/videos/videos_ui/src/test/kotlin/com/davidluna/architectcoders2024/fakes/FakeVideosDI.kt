package com.davidluna.architectcoders2024.fakes

import com.davidluna.architectcoders2024.core_domain.data.datastore.LocalPreferencesDataRepository
import com.davidluna.architectcoders2024.core_domain.data.datastore.PreferencesDataSource
import com.davidluna.architectcoders2024.core_domain.usecases.datastore.GetContentKindUseCase
import com.davidluna.architectcoders2024.core_domain.usecases.datastore.PreferencesRepository
import com.davidluna.architectcoders2024.videos_framework.data.remote.datasource.VideosRemoteApi
import com.davidluna.architectcoders2024.test_shared.framework.FakeLocalPreferencesDataSource
import com.davidluna.architectcoders2024.videos_domain.data.VideosDataRepository
import com.davidluna.architectcoders2024.videos_domain.data.VideosDataSource
import com.davidluna.architectcoders2024.videos_domain.usecases.GetVideosUseCase
import com.davidluna.architectcoders2024.videos_domain.usecases.VideosRepository

class FakeVideosDI {

    private val videosDataSource: VideosDataSource by lazy {
        VideosRemoteApi(FakeVideosServiceImpl())
    }

    private val videosRepository: VideosRepository by lazy {
        VideosDataRepository(videosDataSource)
    }
    private val local: PreferencesDataSource by lazy {
        FakeLocalPreferencesDataSource()
    }
    private val preferencesRepository: PreferencesRepository by lazy {
        LocalPreferencesDataRepository(
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