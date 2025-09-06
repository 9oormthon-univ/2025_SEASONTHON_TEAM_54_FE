package org.ssg_tab.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.ssg_tab.core.designsystem.theme.SsgTabTheme
import org.ssg_tab.core.util.noRippleClickable

@Preview(showBackground = true)
@Composable
private fun PreviewSsgTabButton() {
    SsgTabTheme {
        Column {
            SsgTabButton(
                name = "이메일로 시작하기",
                isEnabled = true,
                onClick = {}
            )
            Spacer(modifier = Modifier.padding(10.dp))
            SsgTabButton(
                name = "이메일로 시작하기",
                isEnabled = false,
                onClick = {}
            )
        }
    }
}

@Composable
fun SsgTabButton(
    name: String,
    isEnabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colorbursh = Brush.verticalGradient(
        colors = listOf(
            SsgTabTheme.colors.MainBlue,
            SsgTabTheme.colors.SubBlue
        )
    )
    val noncolorbursh = Brush.verticalGradient(
        colors = listOf(
            SsgTabTheme.colors.LightGray,
            SsgTabTheme.colors.LightGray
        )
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = if (isEnabled) colorbursh else noncolorbursh,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(vertical = 18.dp, horizontal = 16.dp)
            .clip(RoundedCornerShape(20.dp))
            .noRippleClickable { onClick() }
    ) {
        Text(
            text = name,
            color = SsgTabTheme.colors.White,
            style = SsgTabTheme.typography.Regular_Sb
        )
    }
}
