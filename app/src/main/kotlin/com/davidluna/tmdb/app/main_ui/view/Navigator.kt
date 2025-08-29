package com.davidluna.tmdb.app.main_ui.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.davidluna.tmdb.app.main_ui.presenter.MainEvent
import com.davidluna.tmdb.app.main_ui.view.composables.DrawerScaffoldView
import com.davidluna.tmdb.auth_domain.entities.UserAccount
import com.davidluna.tmdb.auth_ui.navigation.InitialNavigation
import com.davidluna.tmdb.auth_ui.navigation.authNavGraph
import com.davidluna.tmdb.auth_ui.navigation.splashNavGraph
import com.davidluna.tmdb.media_domain.entities.Catalog
import com.davidluna.tmdb.media_ui.navigation.MediaNavigation.MediaCatalog
import com.davidluna.tmdb.media_ui.navigation.mediaNavGraph

@Composable
fun Navigator(
    navController: NavHostController,
    state: NavigatorState,
    bottomNavItems: List<Catalog>,
    selectedCatalog: Catalog,
    userAccount: UserAccount?,
    onMainEvent: (MainEvent) -> Unit,
) {
    DrawerScaffoldView(
        bottomNavItems = bottomNavItems,
        navigatorState = state,
        selectedCatalog = selectedCatalog,
        userAccount = userAccount,
        onEvent = { onMainEvent(it) }
    ) { paddingValues: PaddingValues ->
        NavHost(
            navController = navController,
            startDestination = InitialNavigation.Init,
            modifier = Modifier
                .padding(paddingValues),
            enterTransition = {
                state.slideInAnimation(
                    scope = this,
                    isTopLevel = state.navigationUiState.isTopLevel
                )
            },
            exitTransition = {
                state.slideOutAnimation(
                    scope = this,
                    isTopLevel = state.navigationUiState.isTopLevel
                )
            },
            popEnterTransition = {
                state.slideInAnimation(
                    scope = this,
                    isTopLevel = state.navigationUiState.isTopLevel
                )
            },
            popExitTransition = {
                state.slideOutAnimation(
                    scope = this,
                    isTopLevel = state.navigationUiState.isTopLevel
                )
            }
        ) {
            splashNavGraph(
                navigateHome = { state.navigate(MediaCatalog(shouldPopSplash = true)) },
                navigate = { state.navigate(it) }
            )
            authNavGraph { state.navigate(MediaCatalog()) }
            mediaNavGraph { state.navigate(it) }
        }
    }
}