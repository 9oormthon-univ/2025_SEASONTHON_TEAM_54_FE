package org.ssg_tab.presentation.ui.mypage.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.ssg_tab.R
import org.ssg_tab.core.designsystem.theme.SsgTabTheme

data class DayData(
    val dayLabel: String,
    val value: Int,
    val isToday: Boolean = false,
)

@Composable
fun LearningStatsCardWeek(
    weekData: List<DayData>,
    modifier: Modifier = Modifier,
) {
    val totalCount = weekData.sumOf { it.value }
    val description = "지난 7일,\n배움의 순간이\n${totalCount}번 있었어요"

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(30.dp))
            .background(color = SsgTabTheme.colors.White)
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = description,
                style = SsgTabTheme.typography.Regular_R,
                color = SsgTabTheme.colors.Black,
                lineHeight = 24.sp,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                horizontalAlignment = Alignment.End
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_study_arrow_right),
                    contentDescription = "상세보기 화살표",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                BarChart(
                    data = weekData,
                    modifier = Modifier
                        .width(180.dp)
                        .height(100.dp)
                )
            }
        }
    }
}

@Composable
private fun BarChart(
    data: List<DayData>,
    modifier: Modifier = Modifier,
) {
    val maxValue = data.maxOfOrNull { it.value }?.takeIf { it > 0 } ?: 1

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            data.forEach { dayData ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom,
                ) {
                    // '오늘' 데이터일 경우에만 막대 위에 값 표시
                    if (dayData.isToday) {
                        Text(
                            text = dayData.value.toString(),
                            style = SsgTabTheme.typography.Small_Sb,
                            color = SsgTabTheme.colors.MainBlue,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }

                    val barHeightRatio = (dayData.value.toFloat() / maxValue)
                    val barColor = if (dayData.isToday) {
                        SsgTabTheme.colors.MainBlue
                    } else {
                        SsgTabTheme.colors.LightGray
                    }

                    Box(
                        modifier = Modifier
                            .width(16.dp)
                            .fillMaxHeight(fraction = barHeightRatio)
                            .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                            .background(color = barColor)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            data.forEach { dayData ->
                Text(
                    text = dayData.dayLabel,
                    style = SsgTabTheme.typography.Small_R,
                    color = if (dayData.isToday) {
                        SsgTabTheme.colors.MainBlue
                    } else {
                        SsgTabTheme.colors.SoftGray
                    },
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0F0F0)
@Composable
private fun PreviewLearningStatsCard() {
    SsgTabTheme {
        LearningStatsCardWeek(
            modifier = Modifier.padding(16.dp),
            weekData = listOf(
                DayData("월", 2),
                DayData("화", 4),
                DayData("수", 10),
                DayData("목", 3),
                DayData("금", 5),
                DayData("토", 6),
                DayData("일", 8, isToday = true)
            )
        )
    }
}