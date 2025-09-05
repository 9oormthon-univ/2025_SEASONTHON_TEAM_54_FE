package org.ssg_tab.presentation.ui.mypage.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import org.ssg_tab.R

@Composable
fun LevelBadgeIcon(
    level: Int,
    modifier: Modifier = Modifier
) {
    // level 범위 -> 레벨 뱃지 부여
    val iconResId = when (level) {
        in 1..3 -> R.drawable.ic_study_lv1
        in 4..7 -> R.drawable.ic_study_lv2
        in 8..10 -> R.drawable.ic_study_lv3
        else -> R.drawable.ic_study_lv4
    }

    Icon(
        imageVector = ImageVector.vectorResource(id = iconResId),
        contentDescription = "레벨 $level 뱃지",
        modifier = modifier
            .width(54.dp)
            .height(18.dp),
        tint = Color.Unspecified
    )
}