package org.ssg_tab.presentation.ui.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
private fun PreviewHomeActionButton() {
    SsgTabTheme {
        Column {
            HomeActionButton(onClick = {})
        }
    }
}

@Composable
fun HomeActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(SsgTabTheme.colors.White),
        shape = RoundedCornerShape(32.dp),
        modifier = modifier
            .size(width = 130.dp, height = 60.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_home_share),
                contentDescription = "공유 아이콘",
                tint = Color.Unspecified,
                modifier = Modifier
                    .noRippleClickable { onClick() }
            )
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_home_like_off),
                contentDescription = "좋아요 아이콘",
                tint = Color.Unspecified,
                modifier = Modifier
                    .noRippleClickable { onClick() }
            )
        }
    }
}
