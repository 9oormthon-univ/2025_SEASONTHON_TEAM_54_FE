package org.ssg_tab.presentation.ui.login.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.ssg_tab.R
import org.ssg_tab.core.designsystem.theme.SsgTabTheme

@Composable
fun Tutorial2Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_tutorial_2),
            contentDescription = "tutorial_step2",
            tint = Color.Unspecified,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(60.dp))
        Text(
            text = "부담 없이 한 입씩\n쉽고 가볍게, 슥",
            style = SsgTabTheme.typography.Large_R, color = SsgTabTheme.colors.DarkGray,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(60.dp))

        SsgTabTheme {
            StackedCardsEffect()
        }
    }
}

@Composable
fun StackedCardsEffect() {
    Box(
        contentAlignment = Alignment.Center
    ) {
        SingleCard(offsetY = 90.dp)
        SingleCard(offsetY = 60.dp)
        SingleCard(offsetY = 30.dp)
        SingleCard(offsetY = 0.dp)
    }
}

@Composable
private fun SingleCard(offsetY: Dp) {
    Card(
        modifier = Modifier
            .offset(y = offsetY)
            .width(300.dp)
            .height(400.dp),
        shape = RoundedCornerShape(36.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
    }
}

@Preview(showBackground = true)
@Composable
private fun Tutorial2ScreenPreview() {
    SsgTabTheme {
        Tutorial2Screen()
    }
}
