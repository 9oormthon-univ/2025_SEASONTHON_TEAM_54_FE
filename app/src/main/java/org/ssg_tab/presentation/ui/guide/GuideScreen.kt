package org.ssg_tab.presentation.ui.guide

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.ssg_tab.R
import org.ssg_tab.core.designsystem.component.SsgTabTopBar
import org.ssg_tab.core.designsystem.theme.SsgTabTheme
import org.ssg_tab.data.dto.response.study.toProgressItems
import org.ssg_tab.presentation.ui.guide.component.GuideMyCard
import org.ssg_tab.presentation.ui.guide.component.GuideRankCard
import org.ssg_tab.presentation.ui.guide.model.GuideViewModel
import org.ssg_tab.presentation.ui.storage.component.AnimatedBottomSheet
import org.ssg_tab.presentation.ui.storage.component.DayData
import org.ssg_tab.presentation.ui.storage.component.LearningStatsCard
import org.ssg_tab.presentation.ui.storage.component.ProgressSummaryCard

@Preview(showBackground = true)
@Composable
private fun PreviewGuideScreen() {
    SsgTabTheme {
        GuideScreen()
    }
}

@Composable
fun GuideScreen(
    modifier: Modifier = Modifier,
    viewModel: GuideViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = SsgTabTheme.colors.WhiteGray)
    ) {
        when {
            state.isLoading -> {
                LoadingScreen()
            }

            else -> {
                val studyData = state.studyData
                val userName = studyData?.nickname ?: "사용자"
                val userLevel = "lv.${studyData?.level ?: 1}"
                val progressItems = studyData?.toProgressItems() ?: emptyList()

                val totalProgress = if (progressItems.isNotEmpty()) {
                    progressItems.map { it.progress }.average().toFloat()
                } else 0f

                val currentCount = progressItems.sumOf { (it.progress * 100).toInt() }
                val totalCount = progressItems.size * 100

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(bottom = 220.dp)
                ) {
                    SsgTabTopBar(
                        leftIcon = R.drawable.ic_study_tob,
                        middleText = "${userName}'s 탭",
                        rightIcon = R.drawable.ic_study_my_profile
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp)
                    ) {
                        GuideMyCard(
                            name = userName,
                            level = userLevel,
                            profileImageUrl = studyData?.profileImageUrl
                        )
                        Spacer(modifier = Modifier.width(32.dp))

                        GuideRankCard(
                            rankTitle = "독서왕"
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    ProgressSummaryCard(
                        title = "전체 진행률",
                        currentCount = currentCount,
                        totalCount = totalCount,
                        overallProgress = totalProgress,
                        progressItems = progressItems,
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    LearningStatsCard(
                        title = "학습 통계",
                        description = "지난 7일,\n배움의 순간이\n36번 있었어요",
                        weekData = getDefaultWeekData(),
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                    )

                    Spacer(modifier = Modifier.height(40.dp))
                }

                AnimatedBottomSheet(
                    title = "위로 끌어올려\n다음 퀴즈 풀기",
                    expandedContent = {
                        // QuizScreenWithViewModel 구현 필요
                    },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(horizontal = 20.dp)
                )
            }
        }
    }
}


@Composable
private fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}


private fun getDefaultWeekData(): List<DayData> {
    return listOf(
        DayData("월", 2),
        DayData("화", 4),
        DayData("수", 6),
        DayData("목", 3),
        DayData("금", 5),
        DayData("토", 4),
        DayData("일", 3, isToday = true)
    )
}