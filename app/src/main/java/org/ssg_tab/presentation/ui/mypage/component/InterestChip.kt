package org.ssg_tab.presentation.ui.mypage.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.ssg_tab.core.designsystem.theme.SsgTabTheme

@Composable
fun InterestChip(
    text: String,
    @DrawableRes iconResId: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    // 카테고리 칩 선택 -> 배경, 텍스트, 테두리 색상 변경
    val backgroundColor = if (isSelected) SsgTabTheme.colors.LightBlue else SsgTabTheme.colors.White
    val textColor = if (isSelected) SsgTabTheme.colors.Black else SsgTabTheme.colors.SoftGray
    val borderColor = if (isSelected) SsgTabTheme.colors.MainBlue else SsgTabTheme.colors.SoftGray

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor)
            .border(1.dp, borderColor, RoundedCornerShape(20.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = text,
            modifier = Modifier.size(60.dp)
        )

        Text(
            text = text,
            style = SsgTabTheme.typography.Small_Sb.copy(color = textColor),
            textAlign = TextAlign.Center
        )
    }
}