package com.davidluna.architectcoders2024.media_ui.view.media.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.davidluna.architectcoders2024.core_ui.theme.dimens.Dimens
import com.davidluna.architectcoders2024.media_domain.media_domain_entities.Media
import kotlinx.coroutines.flow.Flow

@Composable
fun MediaLazyRow(
    title: Int,
    flow: Flow<PagingData<Media>>,
    onMovieClicked: (Int, String) -> Unit
) {

    val movies: LazyPagingItems<Media> = flow.collectAsLazyPagingItems()

    ReelView(
        title = stringResource(title),
        movies = movies,
    ) { movieId, movieTitle -> onMovieClicked(movieId, movieTitle) }
    Spacer(modifier = Modifier.padding(top = Dimens.margins.xLarge))

}
