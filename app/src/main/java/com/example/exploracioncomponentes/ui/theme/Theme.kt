package com.example.exploracioncomponentes.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Indigo, onPrimary = Color.White, primaryContainer = IndigoLight,
    secondary = Teal, onSecondary = Color.White, secondaryContainer = TealLight,
    tertiary = Color(0xFF9A4D00), tertiaryContainer = Color(0xFFFFDCC2),
    background = Canvas, surface = Canvas, onBackground = Ink, onSurface = Ink,
    surfaceVariant = Color(0xFFEEF0F8), onSurfaceVariant = Color(0xFF5B5D67)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFBBC3FF), onPrimary = Color(0xFF0B1A68),
    secondary = Color(0xFF80CBC4), background = Night, surface = NightSurface,
    surfaceVariant = Color(0xFF292C34)
)

@Composable
fun ExploracionComponentesTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = if (darkTheme) DarkColors else LightColors, typography = Typography, content = content)
}
