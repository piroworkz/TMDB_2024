package com.davidluna.tmdb.core_ui.theme.dimens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.davidluna.tmdb.core_ui.R

data object Margins {
    /** 0 dp **/
    val zero: Dp
        @Composable
        @ReadOnlyComposable
        get() = dimensionResource(id = R.dimen.zero)

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

    /** 64 dp **/
    val xxLarge: Dp
        @Composable
        @ReadOnlyComposable
        get() = dimensionResource(id = R.dimen.xxlarge)

    /** 128 dp **/
    val xxxLarge: Dp
        @Composable
        @ReadOnlyComposable
        get() = dimensionResource(id = R.dimen.xxxlarge)

    /** 150 dp **/
    val minImageSize: Dp
        @Composable
        @ReadOnlyComposable
        get() = dimensionResource(id = R.dimen.minImageSize)

}
