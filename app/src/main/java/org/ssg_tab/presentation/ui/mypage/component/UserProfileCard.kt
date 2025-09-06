package org.ssg_tab.presentation.ui.mypage.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import org.ssg_tab.R
import org.ssg_tab.core.designsystem.theme.SsgTabTheme

@Preview(showBackground = true)
@Composable
private fun PreviewUserProfileCard() {
    SsgTabTheme {
        UserProfileCard(
            name = "홍길동",
            level = 2,
            imageUrl = null,
            onEditClick = {}
        )
    }
}

@Composable
fun UserProfileCard(
    name: String,
    level: Int,
    imageUrl: String? = null,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = SsgTabTheme.colors.MainBlue)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            if (imageUrl != null && imageUrl.isNotBlank()) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "프로필 이미지",
                    modifier = Modifier
                        .size(53.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop

                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.ic_study_my_profile),
                    contentDescription = "프로필 이미지",
                    modifier = Modifier
                        .size(53.dp)
                        .clip(CircleShape)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = name,
                    style = SsgTabTheme.typography.Regular_Sb.copy(
                        fontSize = 16.sp,
                        color = SsgTabTheme.colors.White,
                    )
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "LV.$level",
                        style = SsgTabTheme.typography.Regular_R.copy(
                            color = SsgTabTheme.colors.White,
                        )
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    LevelBadgeIcon(level = level)
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onEditClick,
                modifier = Modifier
                    .width(45.dp)
                    .height(27.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    "편집",
                    style = SsgTabTheme.typography.Small_R,
                )
            }
        }
    }
}

@Composable
private fun LevelBadgeIcon(level: Int) {
    val iconRes = when (level) {
        1 -> R.drawable.ic_study_lv1
        2 -> R.drawable.ic_study_lv2
        3 -> R.drawable.ic_study_lv3
        else -> R.drawable.ic_study_lv1
    }

    Icon(
        imageVector = ImageVector.vectorResource(id = iconRes),
        contentDescription = "레벨 $level 뱃지",
        tint = Color.Unspecified,
        modifier = Modifier.size(16.dp)
    )
}