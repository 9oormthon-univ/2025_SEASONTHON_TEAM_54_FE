package org.ssg_tab.presentation.ui.guide.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.ssg_tab.R
import org.ssg_tab.core.designsystem.theme.SsgTabTheme

@Preview(showBackground = true)
@Composable
private fun PreviewGuideRankCard() {
    SsgTabTheme {
        GuideRankCard(
            rankTitle = "독서왕"
        )
    }
}

@Composable
fun GuideRankCard(
    rankTitle: String,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(
                color = SsgTabTheme.colors.LightBlue,
                shape = RoundedCornerShape(30.dp)
            )
            .clip(RoundedCornerShape(30.dp))
            .border(
                width = 1.dp,
                color = SsgTabTheme.colors.MainBlue,
                shape = RoundedCornerShape(30.dp)
            )
            .size(120.dp)

    ) {
        Icon(
            modifier = Modifier
                .offset(y = (-52).dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_study_my_profile),
            contentDescription = null,
            tint = SsgTabTheme.colors.LightGray,
        )
        Text(
            modifier = Modifier
                .offset(y = 12.dp),
            text = rankTitle,
            color = SsgTabTheme.colors.DarkGray,
            style = SsgTabTheme.typography.Regular_Sb,
        )
    }
}