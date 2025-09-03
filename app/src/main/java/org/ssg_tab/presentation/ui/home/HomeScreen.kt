package org.ssg_tab.presentation.ui.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.ssg_tab.core.designsystem.theme.SsgTabTheme

@Composable
fun HomeScreen (
    modifier: Modifier = Modifier
) {
    Text(
        "홈 화면",
        color = SsgTabTheme.colors.Black,
        style = SsgTabTheme.typography.header,
    )
}