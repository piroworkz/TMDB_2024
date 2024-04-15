package com.davidluna.architectcoders2024.app.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = White,
    primaryContainer = Tertiary,
    onPrimaryContainer = Primary,
    inversePrimary = Tertiary,
    secondary = Secondary,
    onSecondary = White,
    secondaryContainer = Tertiary,
    onSecondaryContainer = Primary,
    tertiary = Tertiary,
    onTertiary = Primary,
    tertiaryContainer = Tertiary,
    onTertiaryContainer = Primary,
    background = White,
    onBackground = Primary,
    surface = White,
    onSurface = Primary,
    surfaceVariant = Tertiary,
    onSurfaceVariant = Primary,
    surfaceTint = Primary,
    inverseSurface = Tertiary,
    inverseOnSurface = Primary
)

private val DarkColorScheme = lightColorScheme()

@Composable
fun ArchitectCoders2024Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}