package org.ssg_tab.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val TextBlue = Color(0xFF0073F7)
val MainBlue = Color(0xFF4699F8)
val SubBlue = Color(0xFF57B5F9)
val LightBlue = Color(0xFFEFF9FE)

val Black = Color(0xFF1A1A1A)
val DarkGray = Color(0xFF28303F)
val MidGray = Color(0xFF5F6474)
val LightGray = Color(0xFFDDE0E8)
val White = Color(0xFFFFFFFE)

@Immutable
data class SsgTabColors(
    val TextBlue: Color,
    val MainBlue: Color,
    val SubBlue: Color,
    val LightBlue: Color,
    val Black: Color,
    val DarkGray: Color,
    val MidGray: Color,
    val LightGray: Color,
    val White: Color,
)

val defaultSsgTabColors = SsgTabColors(
    TextBlue = TextBlue,
    MainBlue = MainBlue,
    SubBlue = SubBlue,
    LightBlue = LightBlue,
    Black = Black,
    DarkGray = DarkGray,
    MidGray = MidGray,
    LightGray = LightGray,
    White = White,
)

val LocalSsgTabColors = staticCompositionLocalOf { defaultSsgTabColors }