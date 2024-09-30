package com.davidluna.tmdb.auth_ui.di

import com.davidluna.tmdb.auth_ui.presenter.LoginViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authViewModelModule = module {
    viewModelOf(::LoginViewModel)
}