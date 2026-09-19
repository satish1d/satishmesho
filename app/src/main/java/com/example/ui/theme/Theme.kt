package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = MeeshoPinkLight,
    onPrimary = Color.White,
    primaryContainer = MeeshoPinkDark,
    onPrimaryContainer = MeeshoPinkSoft,
    secondary = MeeshoPinkAccent,
    onSecondary = Color.White,
    tertiary = MeeshoOrange,
    background = Color(0xFF131418),
    surface = Color(0xFF1E2024),
    onBackground = Color(0xFFF3F4F6),
    onSurface = Color(0xFFF3F4F6),
    surfaceVariant = Color(0xFF2C2F36),
    onSurfaceVariant = Color(0xFFD1D5DB),
    outline = Color(0xFF4B5563)
)

private val LightColorScheme = lightColorScheme(
    primary = MeeshoPink,
    onPrimary = Color.White,
    primaryContainer = MeeshoPinkSoft,
    onPrimaryContainer = MeeshoPinkDark,
    secondary = MeeshoPinkAccent,
    onSecondary = Color.White,
    secondaryContainer = MeeshoPinkSoft,
    onSecondaryContainer = MeeshoPinkDark,
    tertiary = MeeshoOrange,
    background = MeeshoBg,
    surface = MeeshoSurface,
    onBackground = MeeshoTextPrimary,
    onSurface = MeeshoTextPrimary,
    surfaceVariant = MeeshoSurfaceSubtle,
    onSurfaceVariant = MeeshoTextSecondary,
    outline = MeeshoBorder
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Use intentional Meesho brand palette
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

