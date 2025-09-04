package org.ssg_tab.presentation.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.ssg_tab.core.designsystem.theme.SsgTabTheme
import org.ssg_tab.core.util.noRippleClickable

@Preview(showBackground = true)
@Composable
private fun PreviewHomeCategoryChip() {
    SsgTabTheme {
        HomeCategoryChip(
            title = "카테고리",
            isEnabled = true,
            onClick = {}
        )
    }
}

@Composable
fun HomeCategoryChip(
    title: String,
    isEnabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .background(
                color = if (isEnabled) SsgTabTheme.colors.LightBlue else SsgTabTheme.colors.White,
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .noRippleClickable
            { onClick() }
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center)
    {
        Text(
            text = title,
            style = SsgTabTheme.typography.Regular_R,
            color = SsgTabTheme.colors.SoftGray
        )
    }
}