package com.davidluna.architectcoders2024.main_ui.view.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.davidluna.architectcoders2024.core_ui.composables.AppBarView
import com.davidluna.architectcoders2024.core_ui.composables.appGradient
import com.davidluna.architectcoders2024.main_ui.presenter.MainEvent
import com.davidluna.architectcoders2024.main_ui.presenter.MainState
import com.davidluna.architectcoders2024.main_ui.view.NavigatorState
import com.davidluna.architectcoders2024.navigation.domain.destination.DrawerItem

@Composable
fun NavigatorState.DrawerScaffoldView(
    state: MainState,
    appBarTitle: String?,
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
                    onDrawerItemSelected(
                        drawerDestination = drawerItem,
                        sendEvent = { sendEvent(it) }
                    )
                }
            }
        },
        modifier = Modifier
            .background(appGradient()),
        drawerState = drawer.state,
        gesturesEnabled = isTopLevel
    ) {
        Scaffold(
            modifier = Modifier
                .background(appGradient())
                .fillMaxSize(),
            topBar = {
                AppBarView(
                    title = appBarTitle,
                    topLevel = isTopLevel,
                    hideAppBar = hideAppBar,
                    onNavigationIconClick = { onNavDrawerClick() }
                )
            },
            containerColor = Color.Transparent
        ) { paddingValues: PaddingValues ->
            content(paddingValues)
        }
    }

}