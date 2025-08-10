package com.davidluna.tmdb.auth_ui.view.splash

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.davidluna.tmdb.auth_ui.navigation.AuthNavigation
import com.davidluna.tmdb.auth_ui.presenter.splash.SplashViewModel
import com.davidluna.tmdb.auth_ui.view.splash.holder.SplashStateHolder
import com.davidluna.tmdb.auth_ui.view.splash.holder.invoke
import com.davidluna.tmdb.auth_ui.view.splash.holder.rememberSplashState
import com.davidluna.tmdb.core_ui.R
import com.davidluna.tmdb.core_ui.navigation.Destination

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    navigateHome: () -> Unit,
    navigate: (Destination) -> Unit,
) {
    val holder: SplashStateHolder = rememberSplashState(
        onPermissionsResults = { viewModel.checkSessionStatus() },
        navigateHome = { navigateHome() },
        navigate = { navigate(it) }
    )
    LaunchedEffect(viewModel.isLoggedIn.value) {
        viewModel.isLoggedIn.value?.let {
            if (it) holder.authenticate() else navigate(AuthNavigation.Login())
        }
    }
    SplashScreen(holder = holder)
}

@Composable
fun SplashScreen(holder: SplashStateHolder) {
    SideEffect { holder.startAnimation() }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_v1),
            contentDescription = stringResource(R.string.logo_content_description),
            modifier = Modifier
                .graphicsLayer {
                    scaleX = holder.scale()
                    scaleY = holder.scale()
                    createBlurEffect(holder)
                }
        )
    }
}

private fun GraphicsLayerScope.createBlurEffect(holder: SplashStateHolder) {
    if (holder.blur() > 0 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        renderEffect = RenderEffect.createBlurEffect(
            holder.blur(),
            holder.blur(),
            Shader.TileMode.DECAL
        ).asComposeRenderEffect()
    }
}