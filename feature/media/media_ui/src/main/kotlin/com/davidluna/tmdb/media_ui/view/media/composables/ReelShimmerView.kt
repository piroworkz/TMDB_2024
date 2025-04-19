package com.davidluna.tmdb.media_ui.view.media.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davidluna.tmdb.core_ui.composables.shimmer
import com.davidluna.tmdb.core_ui.theme.TmdbTheme
import com.davidluna.tmdb.core_ui.theme.dimens.Dimens

@Composable
fun ReelShimmerView(loading: Boolean = true) {
    val imageSize = LocalConfiguration.current.screenWidthDp.dp / 2
    val boxHeight = remember { derivedStateOf { imageSize + (imageSize / 6) } }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(boxHeight.value)
            .shimmer(loading)
            .padding(top = Dimens.margins.xLarge)
    ) {

    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun MediaScreenShimmerPreview() {
    TmdbTheme {
        ReelShimmerView()
    }
}