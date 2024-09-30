package com.davidluna.tmdb.videos_ui.di

import com.davidluna.tmdb.videos_ui.presenter.VideoPlayerViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val videosViewModelModule = module {
    viewModelOf(::VideoPlayerViewModel)
}