package org.ssg_tab.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.ssg_tab.R

val SsgTabFont = FontFamily(Font(R.font.pretendardblack))

@Immutable
data class SsgTabTypography(
    val header :TextStyle,
    val Large : TextStyle,
    val Regular : TextStyle,
    val Small : TextStyle
)

val defaultSsgTabTypography = SsgTabTypography(
    header = TextStyle(
        fontFamily = SsgTabFont,
        fontWeight = FontWeight.Black,
        fontSize = 24.sp,
    ),
    Large = TextStyle(
        fontFamily = SsgTabFont,
        fontWeight = FontWeight.Black,
        fontSize = 20.sp,
    ),
    Regular = TextStyle(
        fontFamily = SsgTabFont,
        fontWeight = FontWeight.Black,
        fontSize = 14.sp,
    ),
    Small = TextStyle(
        fontFamily = SsgTabFont,
        fontWeight = FontWeight.Black,
        fontSize = 12.sp,
    )
)

val LocalSsgTabTypography = staticCompositionLocalOf { defaultSsgTabTypography }