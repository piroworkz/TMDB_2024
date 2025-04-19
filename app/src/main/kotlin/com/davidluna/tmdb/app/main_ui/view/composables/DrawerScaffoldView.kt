package com.davidluna.tmdb.app.main_ui.view.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.davidluna.tmdb.app.main_ui.presenter.MainEvent
import com.davidluna.tmdb.app.main_ui.presenter.MainViewModel
import com.davidluna.tmdb.app.main_ui.view.NavigatorState
import com.davidluna.tmdb.core_ui.composables.AppBarView
import com.davidluna.tmdb.core_ui.composables.appGradient
import com.davidluna.tmdb.core_ui.navigation.destination.DrawerItem

@Composable
fun DrawerScaffoldView(
    state: MainViewModel.MainState,
    navigatorState: NavigatorState,
    sendEvent: (MainEvent) -> Unit,
    content: @Composable (PaddingValues) -> Unit,
) {

    ModalNavigationDrawer(
        drawerContent = {
            NavDrawerView(
                isGuest = state.user == null,
                user = state.user
            ) { drawerItem: DrawerItem? ->
                drawerItem?.let { sendEvent(navigatorState.onDrawerItemSelected(it)) }
            }
        },
        modifier = Modifier
            .background(appGradient()),
        drawerState = navigatorState.drawerState,
        gesturesEnabled = navigatorState.navigationUiState.isTopLevel
    ) {
        Scaffold(
            modifier = Modifier
                .background(appGradient())
                .fillMaxSize(),
            topBar = {
                if (!navigatorState.navigationUiState.hideAppBar) {
                    AppBarView(
                        title = navigatorState.navigationUiState.appBarTitle,
                        topLevel = navigatorState.navigationUiState.isTopLevel,
                        onNavigationIconClick = { navigatorState.onNavDrawerClick() }
                    )
                }
            },
            containerColor = Color.Transparent
        ) { paddingValues: PaddingValues ->
            content(paddingValues)
        }
    }
}