package com.davidluna.tmdb.app.main_ui.view

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import com.davidluna.tmdb.app.main_ui.presenter.MainEvent
import com.davidluna.tmdb.app.main_ui.presenter.MainEvent.OnCloseSession
import com.davidluna.tmdb.app.main_ui.presenter.MainEvent.SetContentKind
import com.davidluna.tmdb.app.main_ui.view.composables.NavDrawerState
import com.davidluna.tmdb.core_domain.entities.ContentKind.MOVIE
import com.davidluna.tmdb.core_domain.entities.ContentKind.TV_SHOW
import com.davidluna.tmdb.core_ui.navigation.destination.AuthNavigation.Splash
import com.davidluna.tmdb.core_ui.navigation.destination.Destination
import com.davidluna.tmdb.core_ui.navigation.destination.DrawerItem
import com.davidluna.tmdb.core_ui.navigation.destination.MediaNavigation.Detail
import com.davidluna.tmdb.core_ui.navigation.destination.MediaNavigation.MediaCatalog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NavigatorState(
    private val navController: NavHostController,
    private val drawerState: NavDrawerState,
    backStackEntry: Flow<NavBackStackEntry>,
    scope: CoroutineScope,
) {

    init {
        collectFlow(scope, backStackEntry)
    }

    val drawer
        get() = drawerState

    val controller
        get() = navController

    var isTopLevel by mutableStateOf(false)
        private set

    var hideAppBar by mutableStateOf(false)
        private set

    var appBarTitle: String? by mutableStateOf(null)
        private set

    fun onNavDrawerClick() {
        if (isTopLevel) {
            drawer.toggleState()
        } else {
            popBackStack()
        }
    }

    fun popBackStack() {
        navController.popBackStack()
    }

    fun navigateTo(
        destination: Destination,
        optionsBuilder: (NavOptionsBuilder.() -> Unit) = {
            popUpTo(destination) { inclusive = true }
        },
    ) {
        navController.navigate(
            route = destination,
            builder = { optionsBuilder() }
        )
    }

    fun onDrawerItemSelected(
        drawerDestination: DrawerItem?,
        sendEvent: (MainEvent) -> Unit,
    ) {
        when (drawerDestination) {
            DrawerItem.CloseSession -> sendEvent(OnCloseSession)
            DrawerItem.Movies -> sendEvent(SetContentKind(MOVIE))
            DrawerItem.TvShows -> sendEvent(SetContentKind(TV_SHOW))
            null -> {}
        }
        drawer.toggleState()
    }

    private fun collectFlow(
        scope: CoroutineScope,
        backStackEntry: Flow<NavBackStackEntry>,
    ) {
        scope.launch {
            backStackEntry.collect {
                it.arguments?.let { args ->
                    hideAppBar = args.getBoolean(Splash::hideAppBar.name)
                    isTopLevel = args.getBoolean(MediaCatalog::topLevel.name)
                    appBarTitle = args.getString(Detail::appBarTitle.name)
                }
            }
        }
    }
}
