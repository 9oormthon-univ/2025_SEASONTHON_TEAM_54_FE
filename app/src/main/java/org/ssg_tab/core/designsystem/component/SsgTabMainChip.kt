package org.ssg_tab.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
private fun PreviewSsgTabMainChip() {
    SsgTabTheme {
        Column {
            SsgTabMainChip(
                title = "20-24세",
                isEnabled = false,
                isBorder = false,
                onClick = {}
            )
            Spacer(modifier = Modifier.padding(10.dp))
            SsgTabMainChip(
                title = "20-24세",
                isEnabled = true,
                isBorder = true,
                onClick = {}
            )
        }
    }
}

@Composable
fun SsgTabMainChip(
    title: String,
    isEnabled: Boolean,
    isBorder: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .background(
                color = if (isEnabled) SsgTabTheme.colors.LightBlue else SsgTabTheme.colors.White,
                shape = RoundedCornerShape(20.dp)
            )
            .border(
                width = 1.dp,
                color = if (isBorder) SsgTabTheme.colors.MainBlue else SsgTabTheme.colors.LightGray,
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .noRippleClickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center) {
        Text(
            text = title,
            style = SsgTabTheme.typography.Regular_R,
            color = if (isEnabled) SsgTabTheme.colors.Black else SsgTabTheme.colors.LightGray
        )
    }

}