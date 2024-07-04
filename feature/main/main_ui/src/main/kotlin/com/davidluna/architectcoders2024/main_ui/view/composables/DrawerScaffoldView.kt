package com.davidluna.architectcoders2024.main_ui.view.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.davidluna.architectcoders2024.core_ui.composables.AppBarView
import com.davidluna.architectcoders2024.main_ui.presenter.MainEvent
import com.davidluna.architectcoders2024.main_ui.presenter.MainState
import com.davidluna.architectcoders2024.main_ui.view.NavigatorState
import com.davidluna.architectcoders2024.navigation.domain.DrawerItem

@Composable
fun NavigatorState.DrawerScaffoldView(
    state: MainState,
    sendEvent: (MainEvent) -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {

    ModalNavigationDrawer(
        drawerContent = {
            if (isTopLevel) {
                NavDrawerView(
                    isGuest = state.user == null,
                    user = state.user
                ) { drawerItem: DrawerItem? ->
                    onDrawerItemSelected(drawerItem, sendEvent)
                }
            }
        },
        drawerState = drawer.state,
        gesturesEnabled = isTopLevel
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                AppBarView(
                    topLevel = isTopLevel,
                    hideAppBar = hideAppBar,
                    onNavigationIconClick = { onNavClick() }
                )
            }
        ) { paddingValues: PaddingValues ->
            content(paddingValues)
        }
    }

}