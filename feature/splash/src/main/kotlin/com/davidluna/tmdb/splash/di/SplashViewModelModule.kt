package com.davidluna.tmdb.splash.di

import com.davidluna.tmdb.splash.presenter.SplashViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val splashViewModelModule = module {
    viewModelOf(::SplashViewModel)
}