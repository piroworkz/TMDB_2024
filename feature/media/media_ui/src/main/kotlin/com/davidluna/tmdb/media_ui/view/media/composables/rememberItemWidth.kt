package com.davidluna.tmdb.media_ui.view.media.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.toSize

@Composable
fun rememberItemWidth(maxItemsPerScreen: Int = 3): Dp {
    val windowInfo = LocalWindowInfo.current
    val density = LocalDensity.current
    return remember(windowInfo, density) {
        derivedStateOf {
            with(density) {
                val containerSize = windowInfo.containerSize.toSize()
                val width = containerSize.width.toDp() / maxItemsPerScreen
                width
            }
        }.value
    }
}