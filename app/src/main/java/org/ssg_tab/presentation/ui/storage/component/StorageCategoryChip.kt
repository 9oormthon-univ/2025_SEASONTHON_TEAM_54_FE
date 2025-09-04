package org.ssg_tab.presentation.ui.storage.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.ssg_tab.core.designsystem.theme.SsgTabTheme
import org.ssg_tab.core.util.noRippleClickable

@Preview(showBackground = true)
@Composable
private fun PreviewStorageCategoryChip() {
    SsgTabTheme {
        Column {
            StorageCategoryChip(
                isEnabled = true,
                content = "전체",
                onClick = {}
            )

            Spacer(modifier = Modifier.padding(4.dp))

            StorageCategoryChip(
                isEnabled = false,
                content = "주식",
                onClick = {}
            )
        }
    }

}

@Composable
fun StorageCategoryChip(
    content: String,
    isEnabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .background(
                color = if (isEnabled) SsgTabTheme.colors.DarkGray else SsgTabTheme.colors.WhiteGray,
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            .noRippleClickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 10.dp)
    ) {
        Text(
            text = content,
            color = if (isEnabled) SsgTabTheme.colors.White else SsgTabTheme.colors.SoftGray,
            style = if (isEnabled) SsgTabTheme.typography.Small_Sb else SsgTabTheme.typography.Small_R
        )

    }
}