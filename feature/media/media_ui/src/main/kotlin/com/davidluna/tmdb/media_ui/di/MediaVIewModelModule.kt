package com.davidluna.tmdb.media_ui.di

import com.davidluna.tmdb.media_ui.presenter.detail.MovieDetailViewModel
import com.davidluna.tmdb.media_ui.presenter.media.MediaCatalogViewModel
import com.davidluna.tmdb.media_ui.presenter.video_player.VideoPlayerViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val mediaViewModelModule = module {
    viewModel { parameter ->
        MovieDetailViewModel(
            movieId = parameter.get(),
            getMovieDetails = get(),
            getMediaImagesUseCase = get(),
            getMediaCastUseCase = get(),
            getContent = get(),
            getContentKindUseCase = get()
        )
    }
    viewModelOf(::MediaCatalogViewModel)
    viewModelOf(::VideoPlayerViewModel)
}