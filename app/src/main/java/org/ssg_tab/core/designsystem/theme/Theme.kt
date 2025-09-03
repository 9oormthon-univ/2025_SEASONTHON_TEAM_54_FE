package org.ssg_tab.core.designsystem.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

object SsgTabTheme {
    val colors: SsgTabColors
        @Composable
        @ReadOnlyComposable
        get() = LocalSsgTabColors.current

    val typography: SsgTabTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalSsgTabTypography.current
}

@Composable
fun ProvideSsgTabColorsAndTypography(
    colors: SsgTabColors,
    typography: SsgTabTypography,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalSsgTabColors provides colors,
        LocalSsgTabTypography provides typography,
        content = content
    )
}

@Composable
fun SsgTabTheme(
    content: @Composable () -> Unit,
) {
    ProvideSsgTabColorsAndTypography(
        colors = defaultSsgTabColors,
        typography = defaultSsgTabTypography
    ) {
        val view = LocalView.current
        if (!view.isInEditMode) {
            SideEffect {
                (view.context as Activity).window.run {
                    WindowCompat.getInsetsController(this, view).isAppearanceLightStatusBars = false
                }
            }
        }
        MaterialTheme(
            content = content
        )
    }
}