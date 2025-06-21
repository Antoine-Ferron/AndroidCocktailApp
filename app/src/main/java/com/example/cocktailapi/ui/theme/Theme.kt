package com.example.cocktailapi.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = FestiveGold,
    secondary = FestiveAqua,
    tertiary = FestiveRed,
    background = DarkBlueBackground,
    onPrimary = DarkBlueBackground,
    onBackground = LightText,
    onSurface = LightText
)

private val LightColorScheme = lightColorScheme(
    primary = FestiveRed,
    secondary = DarkBlueBackground,
    tertiary = FestiveGold,
    background = LightText,
    onPrimary = Color.White,
    onBackground = DarkBlueBackground,
    onSurface = DarkBlueBackground
)

@Composable
fun CocktailApiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        else -> DarkColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}