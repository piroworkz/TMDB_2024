package com.davidluna.tmdb.media_ui.view.media.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.layout.lerp
import androidx.compose.ui.unit.dp
import com.davidluna.tmdb.core_ui.theme.dimens.Dimens
import kotlin.math.absoluteValue

@Composable
fun MediaPager(
    modifier: Modifier = Modifier,
    itemCount: Int,
    initialPage: Int = 0,
    onClick: (Int) -> Unit = {},
    content: @Composable ColumnScope.(Int) -> Unit,
) {
    val state = rememberPagerState(initialPage) { itemCount }
    HorizontalPager(
        state = state,
        pageSize = PageSize.Fill,
        pageSpacing = 0.dp,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.margins.large)
            .wrapContentHeight(),
        contentPadding = PaddingValues(horizontal = 64.dp),
        beyondViewportPageCount = 2
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer { resizeOnSnap(state, it) }
                .clickable { onClick(it) },
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            content(it)
        }
    }
}

fun GraphicsLayerScope.resizeOnSnap(
    pagerState: PagerState,
    page: Int,
) {
    val startScaleFactor = 0.75F
    val pageOffset = (
            (pagerState.currentPage - page) + pagerState
                .currentPageOffsetFraction
            ).absoluteValue
    lerp(
        start = ScaleFactor(startScaleFactor, startScaleFactor),
        stop = ScaleFactor(1f, 1f),
        fraction = 1f - pageOffset.coerceIn(0f, 1f)
    ).also { scaleFactor ->
        scaleX = scaleFactor.scaleX
        scaleY = scaleFactor.scaleY
    }
}