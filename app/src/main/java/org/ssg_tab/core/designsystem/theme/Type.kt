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
val SsgTabSemiBoldFont = FontFamily(Font(R.font.pretendard_semibold))
val SSgTabRegularFont = FontFamily(Font(R.font.pretendard_regular))

@Immutable
data class SsgTabTypography(
    val header :TextStyle,
    val Large_Sb : TextStyle,
    val Large_R: TextStyle,
    val Regular_Sb : TextStyle,
    val Regular_R : TextStyle,
    val Small_Sb : TextStyle,
    val Small_R : TextStyle,
)

val defaultSsgTabTypography = SsgTabTypography(
    header = TextStyle(
        fontFamily = SsgTabSemiBoldFont,
        fontWeight = FontWeight.Black,
        fontSize = 24.sp,
        lineHeight = 34.sp,
    ),
    Large_Sb = TextStyle(
        fontFamily = SsgTabSemiBoldFont,
        fontWeight = FontWeight.Black,
        fontSize = 20.sp,
        lineHeight = 30.sp,
    ),
    Large_R = TextStyle(
        fontFamily = SSgTabRegularFont,
        fontWeight = FontWeight.Black,
        fontSize = 20.sp,
        lineHeight = 30.sp,
    ),
    Regular_Sb = TextStyle(
        fontFamily = SsgTabSemiBoldFont,
        fontWeight = FontWeight.Black,
        fontSize = 14.sp,
        lineHeight = 24.sp,
    ),
    Regular_R = TextStyle(
        fontFamily = SSgTabRegularFont,
        fontWeight = FontWeight.Black,
        fontSize = 14.sp,
        lineHeight = 24.sp,
    ),
    Small_Sb = TextStyle(
        fontFamily = SsgTabSemiBoldFont,
        fontWeight = FontWeight.Black,
        fontSize = 12.sp,
        lineHeight = 18.sp,
    ),
    Small_R = TextStyle(
        fontFamily = SSgTabRegularFont,
        fontWeight = FontWeight.Black,
        fontSize = 12.sp,
        lineHeight = 18.sp,
    ),
)

val LocalSsgTabTypography = staticCompositionLocalOf { defaultSsgTabTypography }