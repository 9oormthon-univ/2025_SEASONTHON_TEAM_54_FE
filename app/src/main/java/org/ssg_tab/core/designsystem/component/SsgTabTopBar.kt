package org.ssg_tab.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.ssg_tab.R
import org.ssg_tab.core.designsystem.theme.SsgTabTheme

@Preview(showBackground = true)
@Composable
private fun PreviewSsgTabTopBar() {
    SsgTabTheme {
        Column {
            SsgTabTopBar(
                leftIcon = R.drawable.ic_core_back,
                middleText = "",
                rightIcon = 0
            )
            Spacer(modifier = Modifier.padding(10.dp))
            SsgTabTopBar(
                leftIcon = R.drawable.ic_core_save,
                middleText = "내 관심목록",
                rightIcon = R.drawable.ic_core_search
            )
        }

    }
}

@Composable
fun SsgTabTopBar(
    leftIcon: Int,
    middleText: String,
    rightIcon: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = SsgTabTheme.colors.White)
            .padding(horizontal = 16.dp, vertical = 12.dp),
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = leftIcon),
            contentDescription = "left icon",
        )

        Text(
            text = middleText,
            color = SsgTabTheme.colors.Black,
            style = SsgTabTheme.typography.header,
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = ImageVector.vectorResource(id = rightIcon),
            contentDescription = "right icon",
        )
    }
}