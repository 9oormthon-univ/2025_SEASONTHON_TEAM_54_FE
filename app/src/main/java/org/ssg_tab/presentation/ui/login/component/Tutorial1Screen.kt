package org.ssg_tab.presentation.ui.login.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.zIndex
import androidx.wear.compose.material.Text
import org.ssg_tab.R
import org.ssg_tab.core.designsystem.theme.SsgTabTheme

@Composable
fun Tutorial1Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_tutorial_1),
            contentDescription = "tutorial_step1",
            tint = Color.Unspecified,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(60.dp))
        Text(
            text = "어렵기만 한 경제\n어디서부터 시작해야 할 지 막막하신가요?",
            style = SsgTabTheme.typography.Large_R, color = SsgTabTheme.colors.DarkGray,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(40.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
        ) {
            TextBubble("코스피 코스닥\n뭐가 다른거야?", Modifier.align(Alignment.TopStart))
            TextBubble("ETF, 펀드, 주식..\n뭐부터 사야 돼?", Modifier.align(Alignment.TopEnd))
            TextBubble("배당금, 시세차익\n너무 복잡해", Modifier.align(Alignment.Center).zIndex(2f))
            TextBubble("정책 가점은\n어떻게 계산해?", Modifier.align(Alignment.BottomStart))
            TextBubble("금리가 오르면\n집값이 왜 내려가?", Modifier.align(Alignment.BottomEnd))
        }

        Spacer(modifier = Modifier.height(60.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "\"기준금리 0.25% 인상, 부동산 시장 전망은...\"", style = SsgTabTheme.typography.Small_R, color = SsgTabTheme.colors.SoftGray)
            Text(text = "\"GDP 성장률 2.1%, 체감 경기는...\"", style = SsgTabTheme.typography.Large_R, color = SsgTabTheme.colors.MidGray)
            Text(text = "\"코스피, 이번 주 2,500선 돌파...\"", style = SsgTabTheme.typography.Small_R, color = SsgTabTheme.colors.SoftGray)
            Text(text = "\"나스닥 급등... 투자 타이밍은?\"", style = SsgTabTheme.typography.Large_R, color = SsgTabTheme.colors.MidGray)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TutorialScreenPreview() {
    SsgTabTheme {
        Tutorial1Screen()
    }
}