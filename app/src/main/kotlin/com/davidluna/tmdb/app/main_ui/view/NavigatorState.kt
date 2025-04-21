package com.davidluna.tmdb.app.main_ui.view

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.tween
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import com.davidluna.tmdb.auth_ui.navigation.AuthNavigation
import com.davidluna.tmdb.core_ui.navigation.Destination
import com.davidluna.tmdb.auth_ui.navigation.InitialNavigation
import com.davidluna.tmdb.media_ui.navigation.MediaNavigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class NavigatorState(
    private val navController: NavHostController,
    private val scope: CoroutineScope,
) {
    val drawerState: DrawerState by mutableStateOf(DrawerState(initialValue = DrawerValue.Closed))
    var navigationUiState by mutableStateOf(NavigationUiState())
        private set

    @Composable
    fun BackStackEntryFlowCollectEffect() {
        LaunchedEffect(Unit) {
            navController.currentBackStackEntryFlow.distinctUntilChanged().collect {
                it.arguments?.let { args ->
                    navigationUiState = navigationUiState.copy(
                        isTopLevel = args.getBoolean(MediaNavigation.MediaCatalog::topLevel.name),
                        hideAppBar = args.getBoolean(AuthNavigation.Login::hideAppBar.name),
                        appBarTitle = args.getString(MediaNavigation.Detail::appBarTitle.name)
                            ?: String()
                    )
                }
            }
        }
    }

    fun navigate(
        destination: Destination,
    ) {
        navController.navigate(destination) { navOptions(destination) }
    }

    fun onNavDrawerClick() {
        if (navigationUiState.isTopLevel) {
            toggleDrawerValue()
        } else {
            popBackStack()
        }
    }

    fun toggleDrawerValue() {
        scope.launch {
            if (drawerState.isClosed) {
                drawerState.open()
            } else {
                drawerState.close()
            }
        }
    }

    fun slideInAnimation(
        scope: AnimatedContentTransitionScope<NavBackStackEntry>,
        isTopLevel: Boolean,
    ): EnterTransition {
        val direction = if (isTopLevel) {
            AnimatedContentTransitionScope.SlideDirection.Up
        } else {
            AnimatedContentTransitionScope.SlideDirection.Down
        }
        return scope.slideIntoContainer(
            towards = direction,
            animationSpec = tween(500, delayMillis = 300, easing = EaseIn)
        )
    }

    fun slideOutAnimation(
        scope: AnimatedContentTransitionScope<NavBackStackEntry>,
        isTopLevel: Boolean,
    ): ExitTransition {
        val direction = if (isTopLevel) {
            AnimatedContentTransitionScope.SlideDirection.Up
        } else {
            AnimatedContentTransitionScope.SlideDirection.Down
        }
        return scope.slideOutOfContainer(
            towards = direction,
            animationSpec = tween(500, delayMillis = 300, easing = EaseIn)
        )
    }

    private fun popBackStack() {
        navController.popBackStack()
    }

    private fun NavOptionsBuilder.navOptions(
        destination: Destination,
    ) = when (destination) {
        is AuthNavigation.Login -> {
            popUpTo(InitialNavigation.Splash()) { inclusive = true }
            launchSingleTop = true
        }

        is MediaNavigation.MediaCatalog -> {
            if (destination.shouldPopSplash) {
                popUpTo(InitialNavigation.Splash()) { inclusive = true }
            } else {
                popUpTo(AuthNavigation.Login()) { inclusive = true }
            }
            launchSingleTop = true
        }

        else -> {
            popUpTo(destination) { inclusive = false }
        }
    }

}