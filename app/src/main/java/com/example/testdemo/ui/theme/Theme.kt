package com.example.testdemo.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = theme_dark_primary,
    onPrimary = theme_dark_onPrimary,
    secondary = theme_dark_secondary,
    onSecondary = theme_dark_onSecondary,
    tertiary = theme_dark_tertiary,
    onTertiary = theme_dark_onTertiary,
    background = theme_dark_background,
    onBackground = theme_dark_onBackground,
    surface = theme_dark_surface,
    onSurface = theme_dark_onSurface,
    inverseSurface = theme_dark_inverseSurface,
    inverseOnSurface = theme_dark_inverseOnSurface,
    inversePrimary = theme_dark_inversePrimary
)

private val LightColorScheme = lightColorScheme(
    primary = theme_light_primary,
    onPrimary = theme_light_onPrimary,
    secondary = theme_light_secondary,
    onSecondary = theme_light_onSecondary,
    tertiary = theme_light_tertiary,
    onTertiary = theme_light_onTertiary,
    background = theme_light_background,
    onBackground = theme_light_onBackground,
    surface = theme_light_surface,
    onSurface = theme_light_onSurface,
    inverseSurface = theme_light_inverseSurface,
    inverseOnSurface = theme_light_inverseOnSurface,
    inversePrimary = theme_light_inversePrimary
)

@Composable
fun TestDemoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
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