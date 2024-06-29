package com.davidluna.architectcoders2024.media_ui.view.media.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import com.davidluna.architectcoders2024.core_ui.R
import com.davidluna.architectcoders2024.core_ui.composables.ErrorDialogView
import com.davidluna.architectcoders2024.core_ui.theme.dimens.Dimens
import com.davidluna.media_domain.media_domain_entities.Media
import kotlinx.coroutines.flow.Flow

@Composable
fun MoviesLazyRow(
    title: Int,
    flow: Flow<PagingData<Media>>,
    onMovieClicked: (Int) -> Unit
) {

    val movies: LazyPagingItems<Media> = flow.collectAsLazyPagingItems()
    val size = LocalConfiguration.current.screenWidthDp.dp / 2

    when (movies.loadState.refresh) {
        is LoadState.Error -> {
            ErrorDialogView(
                error = AppError.Message(
                    0,
                    stringResource(R.string.something_went_wrong)
                )
            ) {
                movies.refresh()
            }
        }

        LoadState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Dimens.margins.xLarge)
                    .size(size),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is LoadState.NotLoading -> {
            ReelView(
                title = stringResource(title),
                movies = movies,
            ) { onMovieClicked(it) }
            Spacer(modifier = Modifier.padding(top = Dimens.margins.xLarge))
        }
    }

}
