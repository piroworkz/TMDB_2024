package com.davidluna.tmdb.app.main_ui.view.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.davidluna.tmdb.app.main_ui.model.DrawerItem
import com.davidluna.tmdb.app.main_ui.model.DrawerItem.CloseSession
import com.davidluna.tmdb.app.main_ui.model.DrawerItem.Movies
import com.davidluna.tmdb.app.main_ui.model.DrawerItem.TvShows
import com.davidluna.tmdb.app.main_ui.presenter.MainEvent
import com.davidluna.tmdb.app.main_ui.presenter.MainEvent.UpdateBottomNavItems
import com.davidluna.tmdb.app.main_ui.view.NavigatorState
import com.davidluna.tmdb.auth_domain.entities.UserAccount
import com.davidluna.tmdb.core_ui.composables.AppBarView
import com.davidluna.tmdb.media_domain.entities.Catalog
import com.davidluna.tmdb.media_domain.entities.MediaType.MOVIE
import com.davidluna.tmdb.media_domain.entities.MediaType.TV_SHOW
import com.davidluna.tmdb.media_ui.view.utils.bottomBarItems

@Composable
fun DrawerScaffoldView(
    bottomNavItems: List<Catalog>,
    navigatorState: NavigatorState,
    selectedCatalog: Catalog,
    userAccount: UserAccount?,
    onEvent: (MainEvent) -> Unit,
    content: @Composable (PaddingValues) -> Unit,
) {

    ModalNavigationDrawer(
        drawerContent = {
            NavDrawerView(
                userAccount = userAccount,
                onSelected = { drawerItem: DrawerItem? ->
                    drawerItem?.let {
                        navigatorState.toggleDrawerValue()
                        when (it) {
                            CloseSession -> onEvent(MainEvent.OnCloseSession)
                            Movies -> onEvent(UpdateBottomNavItems(MOVIE.bottomBarItems()))
                            TvShows -> onEvent(UpdateBottomNavItems(TV_SHOW.bottomBarItems()))
                        }
                    }
                },
                selectedEndpoint = selectedCatalog
            )
        },
        drawerState = navigatorState.drawerState,
        gesturesEnabled = navigatorState.navigationUiState.isTopLevel
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding(),
            topBar = {
                if (!navigatorState.navigationUiState.hideAppBar) {
                    AppBarView(
                        title = navigatorState.navigationUiState.appBarTitle,
                        topLevel = navigatorState.navigationUiState.isTopLevel,
                        onNavigationIconClick = { navigatorState.onNavDrawerClick() }
                    )
                }
            },
            bottomBar = {
                if (navigatorState.navigationUiState.isTopLevel) {
                    AppBottomBar(
                        selectedCatalog = selectedCatalog,
                        bottomNavItems = bottomNavItems,
                        onMediaCatalogSelected = { onEvent(MainEvent.OnCatalogSelected(it)) }
                    )
                }
            }
        ) { paddingValues: PaddingValues ->
            content(paddingValues)
        }
    }
}
