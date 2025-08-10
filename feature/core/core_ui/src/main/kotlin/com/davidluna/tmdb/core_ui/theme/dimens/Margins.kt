package com.davidluna.tmdb.core_ui.theme.dimens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.davidluna.tmdb.core_ui.R

data object Margins {
    /** 1 dp*/
    val thin: Dp
        @Composable
        @ReadOnlyComposable
        get() = dimensionResource(id = R.dimen.one)

    /** 4 dp **/
    val small: Dp
        @Composable
        @ReadOnlyComposable
        get() = dimensionResource(id = R.dimen.small)

    /** 8 dp **/
    val medium: Dp
        @Composable
        @ReadOnlyComposable
        get() = dimensionResource(id = R.dimen.medium)

    /** 16 dp **/
    val large: Dp
        @Composable
        @ReadOnlyComposable
        get() = dimensionResource(id = R.dimen.large)

    /** 32 dp **/
    val xLarge: Dp
        @Composable
        @ReadOnlyComposable
        get() = dimensionResource(id = R.dimen.xlarge)
}
