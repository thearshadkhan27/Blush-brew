package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = AccentCopper,
    onPrimary = Color.White,
    primaryContainer = SurfaceCardElevated,
    onPrimaryContainer = Color.White,
    secondary = AccentBlush,
    onSecondary = Color.White,
    tertiary = AccentGold,
    background = BgNearBlack,
    onBackground = TextWhite,
    surface = SurfaceCard,
    onSurface = TextWhite,
    surfaceVariant = SurfaceCardElevated,
    onSurfaceVariant = TextGrey,
    outline = BorderOutlineWhite,
    outlineVariant = BorderOutlineSubtle
)

@Composable
fun MyApplicationTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = AppTypography,
        content = content
    )
}
