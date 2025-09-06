package org.ssg_tab.presentation.ui.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
            HomeActionButton(
                isLiked = false,
                isLiking = false,
                onShareClick = {},
                onLikeClick = {}
            )
        }
    }
}


@Composable
fun HomeActionButton(
    onShareClick: () -> Unit,
    onLikeClick: () -> Unit,
    isLiked: Boolean = false,
    isLiking: Boolean = false,
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
                    .noRippleClickable { onShareClick() }
            )

            if (isLiking) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    strokeWidth = 2.dp,
                    color = Color(0xFF42A5F5)
                )
            } else {
                Icon(
                    imageVector = ImageVector.vectorResource(
                        if (isLiked) R.drawable.ic_home_like_on else R.drawable.ic_home_like_off
                    ),
                    contentDescription = if (isLiked) "좋아요 취소" else "좋아요",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .noRippleClickable { onLikeClick() }
                )
            }
        }
    }
}
