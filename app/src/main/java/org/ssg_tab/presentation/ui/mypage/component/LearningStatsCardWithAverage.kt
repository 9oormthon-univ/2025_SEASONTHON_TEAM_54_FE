package org.ssg_tab.presentation.ui.mypage.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
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
import kotlin.math.roundToInt


@Composable
fun LearningStatsCardWithAverage(
    weekData: List<DayData>,
    modifier: Modifier = Modifier,
) {
    val totalCount = weekData.sumOf { it.value }
    val averageCount = if (weekData.isNotEmpty()) {
        (totalCount.toFloat() / weekData.size).roundToInt()
    } else {
        0
    }
    val description = "지난 7일,\n하루 평균 ${averageCount}번의\n슥-을 했어요"

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(30.dp))
            .background(color = SsgTabTheme.colors.White)
            .padding(horizontal = 24.dp, vertical = 20.dp)
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

            Column(horizontalAlignment = Alignment.End) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_study_arrow_right),
                    contentDescription = "상세보기 화살표",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                BarChartWithAverage(
                    data = weekData,
                    averageValue = averageCount,
                    modifier = Modifier
                        .width(180.dp)
                        .height(100.dp)
                )
            }
        }
    }
}

@Composable
private fun BarChartWithAverage(
    data: List<DayData>,
    averageValue: Int,
    modifier: Modifier = Modifier,
) {
    val maxValue = data.maxOfOrNull { it.value }?.takeIf { it > 0 } ?: 1

    Column(modifier = modifier) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.BottomStart
        ) {
            val chartHeight = this.maxHeight

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                data.forEach { dayData ->
                    val barHeightRatio = (dayData.value.toFloat() / maxValue)
                    val barColor = if (dayData.isToday) SsgTabTheme.colors.MainBlue else SsgTabTheme.colors.LightGray
                    Box(
                        modifier = Modifier
                            .width(16.dp)
                            .fillMaxHeight(fraction = barHeightRatio)
                            .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                            .background(color = barColor)
                    )
                }
            }

            if (averageValue in 1..maxValue) {
                val averageLineRatio = averageValue.toFloat() / maxValue
                val linePositionFromBottom = chartHeight * averageLineRatio

                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = linePositionFromBottom),
                    thickness = 2.dp,
                    color = SsgTabTheme.colors.MainBlue
                )

                Text(
                    text = averageValue.toString(),
                    style = SsgTabTheme.typography.Small_R.copy(fontSize = 10.sp),
                    color = SsgTabTheme.colors.MainBlue.copy(alpha = 0.8f),
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .offset(
                            x = (-16).dp,
                            y = -linePositionFromBottom + 7.dp
                        )
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            data.forEach { dayData ->
                Text(text = dayData.dayLabel, style = SsgTabTheme.typography.Small_R, color = SsgTabTheme.colors.SoftGray, fontSize = 12.sp, textAlign = TextAlign.Center, modifier = Modifier.width(16.dp))
            }
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFF0F0F0)
@Composable
private fun PreviewLearningStatsCardWithAverage() {
    val sampleData = listOf(
        DayData("월", 40),
        DayData("화", 22),
        DayData("수", 30),
        DayData("목", 7),
        DayData("금", 15),
        DayData("토", 12),
        DayData("일", 24, isToday = true)
    )

    SsgTabTheme {
        LearningStatsCardWithAverage(
            modifier = Modifier.padding(16.dp),
            weekData = sampleData
        )
    }
}