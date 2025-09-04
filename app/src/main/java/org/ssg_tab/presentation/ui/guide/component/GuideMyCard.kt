package org.ssg_tab.presentation.ui.guide.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.ssg_tab.R
import org.ssg_tab.core.designsystem.theme.SsgTabTheme

@Preview(showBackground = true)
@Composable
private fun PreviewGuideMyCard() {
    SsgTabTheme {
        GuideMyCard(
            name = "홍길동",
            level = "Lv.1",
            rank = R.drawable.ic_study_lv1
        )
    }
}

@Composable
fun GuideMyCard(
    name: String,
    level: String,
    rank: Int,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(
                color = SsgTabTheme.colors.MainBlue,
                shape = RoundedCornerShape(30.dp)
            )
            .clip(RoundedCornerShape(30.dp))
            .padding(horizontal = 23.dp, vertical = 27.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_study_my_profile),
                contentDescription = "My Profile Icon",
                tint = Color.Unspecified
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = name,
                    color = SsgTabTheme.colors.White,
                    style = SsgTabTheme.typography.Regular_Sb
                )

                Row {
                    Text(
                        text = level,
                        color = SsgTabTheme.colors.White,
                        style = SsgTabTheme.typography.Regular_Sb
                    )

                    Spacer(modifier = Modifier.padding(start = 6.dp))

                    Icon(
                        imageVector = ImageVector.vectorResource(id = rank),
                        contentDescription = "Rank Icon",
                        tint = Color.Unspecified
                    )
                }
            }
        }

    }
}