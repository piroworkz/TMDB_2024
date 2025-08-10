package com.davidluna.tmdb.core_ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

@Composable
fun TmdbTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}


private val Background = Color(0xFFFDFEFF)
private val Error = Color(0xFFB3261E)
private val ErrorContainer = Color(0xFFF9DEDC)
private val InverseOnSurface = Color(0xFFF1F1F1)
private val InversePrimary = Color(0xFF90CEA1)
private val InverseSurface = Color(0xFF2F3133)
private val OnBackground = Color(0xFF1A1C1E)
private val OnError = Color.White
private val OnErrorContainer = Color(0xFF410E0B)
private val OnPrimary = Color.White
private val OnPrimaryContainer = Color(0xFF0d253f)
private val OnSecondary = Color.Black
private val OnSecondaryContainer = Color.Black
private val OnSurface = Color(0xFF1A1C1E)
private val OnSurfaceVariant = Color(0xFF43474E)
private val OnTertiary = Color.Black
private val OnTertiaryContainer = Color.Black
private val Outline = Color(0xFF73777F)
private val OutlineVariant = Color(0xFFC4C6CB)
private val Primary = Color(0xFF0d253f)
private val PrimaryContainer = Color(0xFFD2E4F7)
private val Scrim = Color(0x66000000)
private val Secondary = Color(0xFF01b4e4)
private val SecondaryContainer = Color(0xFFB3EFFF)
private val Surface = Color(0xFFFFFFFF)
private val SurfaceBright = Color(0xFFFFFFFF)
private val SurfaceContainer = Color(0xFFF3F4F7)
private val SurfaceContainerHigh = Color(0xFFECEEF0)
private val SurfaceContainerHighest = Color(0xFFE6E8EB)
private val SurfaceContainerLow = Color(0xFFF9F9FB)
private val SurfaceContainerLowest = Color(0xFFFFFFFF)
private val SurfaceDim = Color(0xFFE7E9EC)
private val SurfaceTint = Primary
private val SurfaceVariant = Color(0xFFE1E3E7)
private val Tertiary = Color(0xFF90cea1)
private val TertiaryContainer = Color(0xFFCEF1DB)

private val LightColorScheme = lightColorScheme(
    background = Background,
    error = Error,
    errorContainer = ErrorContainer,
    inverseOnSurface = InverseOnSurface,
    inversePrimary = InversePrimary,
    inverseSurface = InverseSurface,
    onBackground = OnBackground,
    onError = OnError,
    onErrorContainer = OnErrorContainer,
    onPrimary = OnPrimary,
    onPrimaryContainer = OnPrimaryContainer,
    onSecondary = OnSecondary,
    onSecondaryContainer = OnSecondaryContainer,
    onSurface = OnSurface,
    onSurfaceVariant = OnSurfaceVariant,
    onTertiary = OnTertiary,
    onTertiaryContainer = OnTertiaryContainer,
    outline = Outline,
    outlineVariant = OutlineVariant,
    primary = Primary,
    primaryContainer = PrimaryContainer,
    scrim = Scrim,
    secondary = Secondary,
    secondaryContainer = SecondaryContainer,
    surface = Surface,
    surfaceBright = SurfaceBright,
    surfaceContainer = SurfaceContainer,
    surfaceContainerHigh = SurfaceContainerHigh,
    surfaceContainerHighest = SurfaceContainerHighest,
    surfaceContainerLow = SurfaceContainerLow,
    surfaceContainerLowest = SurfaceContainerLowest,
    surfaceDim = SurfaceDim,
    surfaceTint = SurfaceTint,
    surfaceVariant = SurfaceVariant,
    tertiary = Tertiary,
    tertiaryContainer = TertiaryContainer
)
private val DarkColorScheme = lightColorScheme()