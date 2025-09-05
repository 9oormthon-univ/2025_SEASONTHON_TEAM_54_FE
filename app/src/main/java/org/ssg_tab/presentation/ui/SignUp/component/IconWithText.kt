package org.ssg_tab.presentation.ui.signup.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.ssg_tab.R
import org.ssg_tab.core.designsystem.theme.SsgTabTheme

@Composable
fun IconWithText(
    iconResId: Int,
    text: String,
    modifier: Modifier = Modifier,
    iconTint: Color = SsgTabTheme.colors.Black,
    textColor: Color = SsgTabTheme.colors.Black,
    textSize: Int = 12,
    textWeight: FontWeight = FontWeight.Normal,
    spacing: Int = 4
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = iconResId),
            contentDescription = text,
            tint = iconTint,
            modifier = Modifier.size(textSize.dp + 4.dp)
        )
        Spacer(modifier = Modifier.width(spacing.dp))
        Text(
            text = text,
            color = textColor,
            fontSize = textSize.sp,
            fontWeight = textWeight,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewIconWithText() {
    SsgTabTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            IconWithText(
                iconResId = R.drawable.ic_login_valid,
                text = "사용 가능한 이메일입니다",
                iconTint = SsgTabTheme.colors.MainBlue,
                textColor = SsgTabTheme.colors.MainBlue,
                textSize = 12,
            )
            Spacer(modifier = Modifier.height(16.dp))
            IconWithText(
                iconResId = R.drawable.ic_login_invalid,
                text = "이미 사용 중인 이메일입니다",
                iconTint = SsgTabTheme.colors.Error,
                textColor = SsgTabTheme.colors.Error,
                textSize = 12,
            )
        }
    }
}