package org.ssg_tab.presentation.ui.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.ssg_tab.R
import org.ssg_tab.core.designsystem.component.SsgTabTopBar
import org.ssg_tab.core.designsystem.theme.SsgTabTheme
import org.ssg_tab.presentation.ui.mypage.component.CardSummary
import org.ssg_tab.presentation.ui.mypage.component.DayData
import org.ssg_tab.presentation.ui.mypage.component.InterestBubblesCard
import org.ssg_tab.presentation.ui.mypage.component.InterestData
import org.ssg_tab.presentation.ui.mypage.component.LearningStatsCardWeek
import org.ssg_tab.presentation.ui.mypage.component.LearningStatsCardWithAverage


val interestDataList = listOf(
    InterestData("주식", 120),
    InterestData("취업", 85),
    InterestData("부동산", 40),
    InterestData("청약", 60)
)

val weekData = listOf(
    DayData("월", 20),
    DayData("화", 4),
    DayData("수", 10),
    DayData("목", 3),
    DayData("금", 5),
    DayData("토", 15),
    DayData("일", 8, isToday = true)
)

@Composable
fun MyHistoryScreen() {

    val backgroundBrush = Brush.verticalGradient( // 그라데이션 배경
        colorStops = arrayOf(
            0.0f to Color.White,
            0.2f to SsgTabTheme.colors.WhiteGray,
        )
    )

    Scaffold(
        containerColor = Color.Transparent,

        topBar = {
            SsgTabTopBar(
                leftIcon = R.drawable.ic_core_back,
                middleText = "",
                rightIcon = null,
            )
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = backgroundBrush)
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp)
        ) {
            item { CardSummary(readCount = 256, favoriteCount = 32) }
            item { InterestBubblesCard(interestDataList) }
            item {LearningStatsCardWeek(weekData = weekData)}
            item { LearningStatsCardWithAverage(weekData = weekData) }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun MyHistoryScreenPreview() {
    SsgTabTheme {
        MyHistoryScreen()
    }
}