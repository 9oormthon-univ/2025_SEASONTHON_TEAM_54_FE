package org.ssg_tab.presentation.ui.storage.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.font.FontWeight
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

@Preview(showBackground = true)
@Composable
private fun PreviewLearningStatsCard() {
    SsgTabTheme {
        LearningStatsCard(
            title = "학습 통계",
            description = "지난 7일,\n배움의 순간이\n36번 있었어요",
            weekData = listOf(
                DayData("월", 2),
                DayData("화", 4),
                DayData("수", 6),
                DayData("목", 3),
                DayData("금", 5),
                DayData("토", 4),
                DayData("일", 3, isToday = true)
            )
        )
    }
}

@Composable
fun LearningStatsCard(
    title: String,
    description: String,
    weekData: List<DayData>,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = SsgTabTheme.colors.White,
                shape = RoundedCornerShape(30.dp)
            )
            .clip(RoundedCornerShape(30.dp))
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        style = SsgTabTheme.typography.Regular_Sb,
                        color = SsgTabTheme.colors.Black,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = description,
                    style = SsgTabTheme.typography.Regular_R,
                    color = SsgTabTheme.colors.Black,
                    lineHeight = 24.sp
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.End
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_study_arrow_right),
                    contentDescription = "화살표",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(16.dp)
                )

                BarChart(
                    data = weekData,
                    modifier = Modifier
                        .width(200.dp)
                        .height(120.dp)
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
    val maxValue = data.maxOfOrNull { it.value } ?: 1

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            data.forEach { dayData ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    if (dayData.isToday) {
                        Text(
                            text = dayData.value.toString(),
                            style = SsgTabTheme.typography.Small_Sb,
                            color = Color(0xFF42A5F5),
                            fontSize = 12.sp,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }

                    val barHeight = if (maxValue > 0) {
                        (dayData.value.toFloat() / maxValue * 80).dp
                    } else {
                        4.dp
                    }

                    Box(
                        modifier = Modifier
                            .width(16.dp)
                            .height(barHeight.coerceAtLeast(4.dp))
                            .background(
                                color = if (dayData.isToday) {
                                    Color(0xFF42A5F5)
                                } else {
                                    Color(0xFFE0E0E0)
                                },
                                shape = RoundedCornerShape(
                                    topStart = 4.dp,
                                    topEnd = 4.dp
                                )
                            )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            data.forEach { dayData ->
                Text(
                    text = dayData.dayLabel,
                    style = SsgTabTheme.typography.Small_R,
                    color = if (dayData.isToday) {
                        Color(0xFF42A5F5)
                    } else {
                        SsgTabTheme.colors.LightGray
                    },
                    fontSize = 12.sp,
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}