package com.davidluna.tmdb.app.main_ui.view

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import com.davidluna.tmdb.app.main_ui.presenter.MainEvent
import com.davidluna.tmdb.app.main_ui.presenter.MainEvent.OnCloseSession
import com.davidluna.tmdb.app.main_ui.presenter.MainEvent.SetContentKind
import com.davidluna.tmdb.core_domain.entities.ContentKind.MOVIE
import com.davidluna.tmdb.core_domain.entities.ContentKind.TV_SHOW
import com.davidluna.tmdb.core_ui.navigation.destination.Destination
import com.davidluna.tmdb.core_ui.navigation.destination.DrawerItem
import com.davidluna.tmdb.core_ui.navigation.destination.MediaNavigation
import com.davidluna.tmdb.core_ui.navigation.destination.StartNavigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class NavigatorState(
    private val navController: NavHostController,
    private val scope: CoroutineScope,
) {
    val drawerState: DrawerState by mutableStateOf(DrawerState(initialValue = DrawerValue.Closed))
    var navigationUiState by mutableStateOf(NavigationUiState())

    @Composable
    fun BackStackEntryFlowCollectEffect() {
        LaunchedEffect(Unit) {
            navController.currentBackStackEntryFlow.distinctUntilChanged().collect {
                it.arguments?.let { args ->
                    navigationUiState = navigationUiState.copy(
                        isTopLevel = args.getBoolean(MediaNavigation.MediaCatalog::topLevel.name),
                        hideAppBar = args.getBoolean(StartNavigation.Splash::hideAppBar.name),
                        appBarTitle = args.getString(MediaNavigation.Detail::appBarTitle.name)
                            ?: String()
                    )
                }
            }
        }
    }

    fun popBackStack() {
        navController.popBackStack()
    }

    fun navigateTo(
        destination: Destination,
        optionsBuilder: (NavOptionsBuilder.() -> Unit) = { popUpTo(destination) { inclusive = true } }
    ) {
        navController.navigate(
            route = destination,
            builder = { optionsBuilder() }
        )
    }

    fun onDrawerItemSelected(
        drawerItem: DrawerItem,
    ): MainEvent {
        toggleDrawerValue()
        return when (drawerItem) {
            DrawerItem.CloseSession -> OnCloseSession
            DrawerItem.Movies -> SetContentKind(MOVIE)
            DrawerItem.TvShows -> SetContentKind(TV_SHOW)
        }

    }

    fun onNavDrawerClick() {
        if (navigationUiState.isTopLevel) {
            toggleDrawerValue()
        } else {
            popBackStack()
        }
    }

    private fun toggleDrawerValue() {
        scope.launch {
            if (drawerState.isClosed) {
                drawerState.open()
            } else {
                drawerState.close()
            }
        }
    }
}
