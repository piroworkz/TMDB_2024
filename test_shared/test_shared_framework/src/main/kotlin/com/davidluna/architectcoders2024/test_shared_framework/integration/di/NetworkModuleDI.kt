package com.davidluna.architectcoders2024.test_shared_framework.integration.di

import com.davidluna.architectcoders2024.auth_data_framework.remote.SessionService
import com.davidluna.architectcoders2024.media_data_framework.remote.services.MediaCatalogService
import com.davidluna.architectcoders2024.media_data_framework.remote.services.MediaDetailService
import com.davidluna.architectcoders2024.test_shared_framework.integration.services.FakeMediaCatalogServiceImpl
import com.davidluna.architectcoders2024.test_shared_framework.integration.services.FakeMediaDetailServiceImpl
import com.davidluna.architectcoders2024.test_shared_framework.integration.services.FakeSessionServiceImpl
import com.davidluna.architectcoders2024.test_shared_framework.integration.services.FakeVideosServiceImpl
import com.davidluna.architectcoders2024.videos_data_framework.remote.service.VideosService


class NetworkModuleDI {
    val sessionService: SessionService by lazy {
        FakeSessionServiceImpl()
    }

    val mediaCatalogService: MediaCatalogService by lazy {
        FakeMediaCatalogServiceImpl()
    }

    val mediaDetailService: MediaDetailService by lazy {
        FakeMediaDetailServiceImpl()
    }

    val videosService: VideosService by lazy {
        FakeVideosServiceImpl()
    }
}