package org.ssg_tab.presentation.ui.login.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import org.ssg_tab.R
import org.ssg_tab.core.designsystem.theme.SsgTabTheme

@Composable
fun Tutorial3Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_tutorial_3),
            contentDescription = "tutorial_step3",
            tint = Color.Unspecified,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(60.dp))
        Text(
            text = "보고 싶을 떈 언제든\n필요한 순간, 탭",
            style = SsgTabTheme.typography.Large_R, color = SsgTabTheme.colors.DarkGray,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(60.dp))

        Card(
            modifier = Modifier
                .width(220.dp)
                .height(280.dp),
            shape = RoundedCornerShape(36.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
        }
        Spacer(modifier = Modifier.height(20.dp))

        // 그림자
        Box(
            modifier = Modifier
                .padding(horizontal = 2.dp, vertical = 0.dp),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_tutorial_3_white),
                contentDescription = "tutorial_step3",
                tint = Color.Unspecified,
            )
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_tutorial_3_blue),
                contentDescription = "tutorial_step3",
                tint = Color.Unspecified,
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
private fun Tutorial3ScreenPreview() {
    SsgTabTheme {
        Tutorial3Screen()
    }
}
