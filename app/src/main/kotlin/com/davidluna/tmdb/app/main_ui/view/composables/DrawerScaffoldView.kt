package com.davidluna.tmdb.app.main_ui.view.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Tv
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davidluna.tmdb.app.main_ui.model.DrawerItem
import com.davidluna.tmdb.app.main_ui.model.DrawerItem.CloseSession
import com.davidluna.tmdb.app.main_ui.model.DrawerItem.Movies
import com.davidluna.tmdb.app.main_ui.model.DrawerItem.TvShows
import com.davidluna.tmdb.app.main_ui.presenter.MainEvent
import com.davidluna.tmdb.app.main_ui.presenter.MainEvent.UpdateBottomNavItems
import com.davidluna.tmdb.app.main_ui.view.NavigatorState
import com.davidluna.tmdb.auth_domain.entities.UserAccount
import com.davidluna.tmdb.core_ui.composables.AppBarView
import com.davidluna.tmdb.core_ui.theme.TmdbTheme
import com.davidluna.tmdb.media_domain.entities.Catalog
import com.davidluna.tmdb.media_domain.entities.MediaType.MOVIE
import com.davidluna.tmdb.media_domain.entities.MediaType.TV_SHOW
import com.davidluna.tmdb.media_ui.view.utils.bottomBarItems
import com.davidluna.tmdb.media_ui.view.utils.getMediaType
import com.davidluna.tmdb.media_ui.view.utils.title

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

@Composable
private fun AppBottomBar(
    modifier: Modifier = Modifier,
    selectedCatalog: Catalog,
    bottomNavItems: List<Catalog>,
    onMediaCatalogSelected: (Catalog) -> Unit,
) {
    Row(
        modifier = modifier
            .padding(BottomAppBarDefaults.ContentPadding)
            .background(MaterialTheme.colorScheme.surface),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        bottomNavItems.forEach {
            val title = it.title?.let { stringResource(it) }.orEmpty()
            TextButton(
                modifier = Modifier
                    .weight(1f),
                onClick = { onMediaCatalogSelected(it) },
                enabled = it != selectedCatalog,
                colors = ButtonColors(
                    containerColor = colorScheme.surface,
                    contentColor = colorScheme.onSurface,
                    disabledContainerColor = colorScheme.onSurfaceVariant.copy(alpha = 0.3F),
                    disabledContentColor = colorScheme.onSurfaceVariant
                )
            ) {
                Image(
                    if (it.getMediaType() == MOVIE) Icons.Outlined.Movie else Icons.Outlined.Tv,
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 8.dp)
                )
                Text(
                    title,
                    maxLines = 1,
                    softWrap = false,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun AppBottomBarPreview() {
    TmdbTheme {
        AppBottomBar(
            selectedCatalog = Catalog.MOVIE_NOW_PLAYING,
            bottomNavItems = MOVIE.bottomBarItems(),
            onMediaCatalogSelected = {}
        )
    }
}