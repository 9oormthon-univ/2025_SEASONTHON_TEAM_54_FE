package org.ssg_tab.presentation.ui.mypage.component


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import org.ssg_tab.core.designsystem.theme.SsgTabTheme


// 데이터 모델
data class InterestData(
    val name: String,
    val count: Int
)


@Composable
fun InterestBubblesCard( // 상위 4개만 표현
    interestDataList: List<InterestData>
) {
    val topFourInterests = interestDataList.sortedByDescending { it.count }.take(4)

    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(16.dp)
        ) {
            Text(
                text = "내가 가장 관심있는 분야는?",
                style = SsgTabTheme.typography.Regular_Sb.copy(
                    color = SsgTabTheme.colors.DarkGray,
                    fontSize = 16.sp
            ))
            Spacer(modifier = Modifier.height(70.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                if (topFourInterests.isNotEmpty()) {
                    // 순위에 따라 각 버블의 위치와 스타일을 다르게 지정
                    topFourInterests.forEachIndexed { index, interest ->
                        val (size, color, modifier) = when (index) {
                            // 1등
                            0 -> Triple(
                                115.dp,
                                SsgTabTheme.colors.Purple,
                                Modifier
                                    .align(Alignment.BottomCenter)
                                    .zIndex(4f)
                            )
                            // 2등
                            1 -> Triple(
                                85.dp,
                                SsgTabTheme.colors.Red,
                                Modifier
                                    .align(Alignment.BottomStart)
                                    .offset(x = 10.dp)
                                    .rotate(-10f)
                                    .zIndex(3f)
                            )
                            // 3등
                            2 -> Triple(
                                70.dp,
                                SsgTabTheme.colors.Green,
                                Modifier
                                    .align(Alignment.BottomEnd)
                                    .offset(x = (-25).dp)
                                    .rotate(10f)
                                    .zIndex(2f)
                            )
                            // 4등
                            else -> Triple(
                                55.dp,
                                SsgTabTheme.colors.Yellow,
                                Modifier
                                    .align(Alignment.Center)
                                    .offset(x = (-75).dp)
                                    .offset(y = (-50).dp)
                                    .rotate(10f)
                                    .zIndex(1f)
                            )
                        }
                        Bubble(
                            modifier = modifier,
                            interest = interest,
                            size = size,
                            color = color
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Bubble(
    modifier: Modifier = Modifier,
    interest: InterestData,
    size: Dp,
    color: Color
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = interest.name,
            style = SsgTabTheme.typography.Small_R,
            textAlign = TextAlign.Center
        )
    }
}

