package org.ssg_tab.presentation.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.ssg_tab.R
import org.ssg_tab.core.designsystem.theme.SsgTabTheme
import org.ssg_tab.core.util.noRippleClickable

@Preview(showBackground = true)
@Composable
private fun PreviewHomeTopBar() {
    SsgTabTheme {
        HomeTopBar(
            onClick = {}
        )
    }
}

@Composable
fun HomeTopBar(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = SsgTabTheme.colors.White
            )
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_bottomnav_study_off),
            contentDescription = "home logo"
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_home_alarm_on),
            contentDescription = "notification",
            tint = Color.Unspecified,
            modifier = modifier
                .noRippleClickable { onClick() }
        )
    }
}