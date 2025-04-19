package com.davidluna.tmdb.app.main_ui.view

import androidx.compose.runtime.Stable

@Stable
data class NavigationUiState(
    val isTopLevel: Boolean = false,
    val hideAppBar: Boolean = true,
    val appBarTitle: String = String(),
)