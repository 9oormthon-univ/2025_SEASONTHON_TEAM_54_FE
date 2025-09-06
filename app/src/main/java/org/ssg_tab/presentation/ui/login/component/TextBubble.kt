package org.ssg_tab.presentation.ui.login.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.ssg_tab.core.designsystem.theme.SsgTabTheme

@Composable
fun TextBubble(text: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.height(140.dp).width(140.dp),
        shape = RoundedCornerShape(32.dp),
        color = SsgTabTheme.colors.White,
        shadowElevation = 7.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 10.dp),
            contentAlignment = Alignment.Center

        ) {
            Text(
                text = text,
                style = SsgTabTheme.typography.Regular_R,
                color = SsgTabTheme.colors.DarkGray,
                textAlign = TextAlign.Center
            )
        }
    }
}