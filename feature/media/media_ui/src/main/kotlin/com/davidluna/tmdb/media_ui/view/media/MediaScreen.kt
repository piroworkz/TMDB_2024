package com.davidluna.tmdb.media_ui.view.media

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.collectAsLazyPagingItems
import com.davidluna.tmdb.core_ui.navigation.destination.MediaNavigation.Detail
import com.davidluna.tmdb.core_ui.theme.TmdbTheme
import com.davidluna.tmdb.core_ui.theme.dimens.Dimens
import com.davidluna.tmdb.media_ui.presenter.media.MediaCatalogViewModel
import com.davidluna.tmdb.media_ui.presenter.media.MediaEvent
import com.davidluna.tmdb.media_ui.presenter.media.MediaEvent.OnMovieClicked
import com.davidluna.tmdb.media_ui.view.media.composables.ReelView

@Composable
fun MediaCatalogScreen(
    state: MediaCatalogViewModel.State,
    sendEvent: (MediaEvent) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            Spacer(modifier = Modifier.padding(top = Dimens.margins.large))
        }
        items(items = state.catalogs) { catalog ->
            val lazyPagingItems = catalog.flow.collectAsLazyPagingItems()
            ReelView(
                title = catalog.catalogName,
                list = lazyPagingItems,
            ) { id, title ->
                sendEvent(OnMovieClicked(Detail(id, title)))
            }

            Spacer(modifier = Modifier.padding(top = Dimens.margins.xLarge))
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun MoviesScreenPreview() {
    TmdbTheme {
        MediaCatalogScreen(
            state = MediaCatalogViewModel.State()
        ) {

        }
    }
}