package org.ssg_tab.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val TextBlue = Color(0xFF0073F7)
val MainBlue = Color(0xFF4699F8)
val SubBlue = Color(0xFF57B5F9)
val LightBlue = Color(0xFFEFF9FE)

val Black = Color(0xFF1A1A1A)
val DarkGray = Color(0xFF28303F)
val MidGray = Color(0xFF5F6474)
val SoftGray = Color(0xFF9094A4)
val LightGray = Color(0xFFDDE0E8)
val WhiteGray = Color(0xFFF1F3F6)
val White = Color(0xFFFFFFFE)
val Error = Color(0xFFDDE0E8)
val LikePink = Color(0xFFFF3B86)

val Yellow = Color(0xFFFFF4B8)
val Green = Color(0xFFD4F7C3)
val Red = Color(0xFFFFCFBE)
val Purple = Color(0xFFCBC3F7)


@Immutable
data class SsgTabColors(
    val TextBlue: Color,
    val MainBlue: Color,
    val SubBlue: Color,
    val LightBlue: Color,
    val Black: Color,
    val DarkGray: Color,
    val MidGray: Color,
    val SoftGray: Color,
    val LightGray: Color,
    val WhiteGray: Color,
    val White: Color,
    val Error: Color,
    val LikePink: Color,
    val Yellow: Color,
    val Green: Color,
    val Red: Color,
    val Purple: Color,
)

val defaultSsgTabColors = SsgTabColors(
    TextBlue = TextBlue,
    MainBlue = MainBlue,
    SubBlue = SubBlue,
    LightBlue = LightBlue,
    Black = Black,
    DarkGray = DarkGray,
    MidGray = MidGray,
    SoftGray = SoftGray,
    LightGray = LightGray,
    WhiteGray = WhiteGray,
    White = White,
    Error = Error,
    LikePink = LikePink,
    Yellow = Yellow,
    Green = Green,
    Red = Red,
    Purple = Purple,
)

val LocalSsgTabColors = staticCompositionLocalOf { defaultSsgTabColors }