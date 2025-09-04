package org.ssg_tab.presentation.ui.storage.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.ssg_tab.R
import org.ssg_tab.core.designsystem.theme.SsgTabTheme

data class ProgressItem(
    val title: String,
    val progress: Float,
)

@Preview(showBackground = true)
@Composable
private fun PreviewProgressSummaryCard() {
    SsgTabTheme {
        ProgressSummaryCard(
            title = "전체 진행률",
            currentCount = 15,
            totalCount = 20,
            overallProgress = 0.75f,
            progressItems = listOf(
                ProgressItem("추식 기초", 0.9f),
                ProgressItem("창업 기초", 0.6f),
                ProgressItem("취업 정보", 0.3f)
            )
        )
    }
}

@Composable
fun ProgressSummaryCard(
    title: String,
    currentCount: Int,
    totalCount: Int,
    overallProgress: Float,
    progressItems: List<ProgressItem>,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = SsgTabTheme.colors.White,
                shape = RoundedCornerShape(30.dp)
            )
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

                    Spacer(modifier = Modifier.width(8.dp))
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "($currentCount/$totalCount)",
                    style = SsgTabTheme.typography.Small_R,
                    color = SsgTabTheme.colors.SoftGray
                )

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier.size(80.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Canvas(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        val strokeWidth = 8.dp.toPx()
                        val radius = (size.width - strokeWidth) / 2
                        val center =
                            androidx.compose.ui.geometry.Offset(size.width / 2, size.height / 2)

                        drawCircle(
                            color = Color.White,
                            radius = radius,
                            center = center,
                            style = Stroke(width = strokeWidth)
                        )

                        val sweepAngle = 360f * overallProgress
                        val gradient = Brush.sweepGradient(
                            colors = listOf(
                                Color(0xFF57B5F9),
                                Color(0xFFEFF9FE),
                            ),
                            center = center
                        )
                        drawArc(
                            brush = gradient,
                            startAngle = -90f,
                            sweepAngle = sweepAngle,
                            useCenter = false,
                            style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
                            topLeft = androidx.compose.ui.geometry.Offset(
                                strokeWidth / 2,
                                strokeWidth / 2
                            ),
                            size = androidx.compose.ui.geometry.Size(
                                size.width - strokeWidth,
                                size.height - strokeWidth
                            )
                        )
                    }

                    Text(
                        text = "${(overallProgress * 100).toInt()}%",
                        style = SsgTabTheme.typography.Regular_Sb,
                        color = SsgTabTheme.colors.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_study_arrow_right),
                        contentDescription = "화살표",
                        tint = Color.Unspecified,
                    )
                }

                progressItems.forEach { item ->
                    ProgressItemRow(
                        title = item.title,
                        progress = item.progress
                    )
                }
            }
        }
    }
}

@Composable
private fun ProgressItemRow(
    title: String,
    progress: Float,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        Text(
            text = title,
            style = SsgTabTheme.typography.Small_R,
            color = SsgTabTheme.colors.MidGray,
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.width(16.dp))

        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .clip(RoundedCornerShape(3.dp)),
            color = SsgTabTheme.colors.MainBlue,
            trackColor = SsgTabTheme.colors.WhiteGray,
            gapSize = 0.dp,
            drawStopIndicator = {}
        )
    }
}