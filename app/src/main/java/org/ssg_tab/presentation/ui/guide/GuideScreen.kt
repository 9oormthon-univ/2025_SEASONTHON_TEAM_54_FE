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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.ssg_tab.R
import org.ssg_tab.core.designsystem.component.SsgTabTopBar
import org.ssg_tab.core.designsystem.theme.SsgTabTheme
import org.ssg_tab.presentation.ui.guide.component.GuideMyCard
import org.ssg_tab.presentation.ui.guide.component.GuideRankCard
import org.ssg_tab.presentation.ui.storage.component.AnimatedBottomSheet
import org.ssg_tab.presentation.ui.storage.component.DayData
import org.ssg_tab.presentation.ui.storage.component.LearningStatsCard
import org.ssg_tab.presentation.ui.storage.component.ProgressItem
import org.ssg_tab.presentation.ui.storage.component.ProgressSummaryCard

@Preview(showBackground = true)
@Composable
private fun PreviewGuideScreen() {
    SsgTabTheme {
        GuideScreen(
            name = "김구름's 탭"
        )
    }
}

@Composable
fun GuideScreen(
    name: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = SsgTabTheme.colors.WhiteGray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 220.dp)
        ) {
            SsgTabTopBar(
                leftIcon = R.drawable.ic_study_tob,
                middleText = name,
                rightIcon = R.drawable.ic_study_my_profile
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
            ) {
                GuideMyCard(
                    name = name,
                    level = "lv.2",
                    rank = R.drawable.ic_study_lv1
                )

                Spacer(modifier = Modifier.width(32.dp))

                GuideRankCard(
                    rankTitle = "독서왕"
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            ProgressSummaryCard(
                title = "전체 진행률",
                currentCount = 15,
                totalCount = 20,
                overallProgress = 0.75f,
                progressItems = listOf(
                    ProgressItem("추식 기초", 0.9f),
                    ProgressItem("창업 기초", 0.6f),
                    ProgressItem("취업 정보", 0.3f)
                ),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

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
                ),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))
        }

        AnimatedBottomSheet(
            title = "위로 끌어올려\n다음 퀴즈 풀기",
            expandedContent = {
                QuizScreen(
                    quizData = QuizData(
                        question = "다음 중 올바른 투자 원칙은?",
                        options = listOf(
                            "모든 돈을 한 곳에 투자한다",
                            "분산 투자를 한다",
                            "감정적으로 투자한다",
                            "빚을 내서 투자한다"
                        ),
                        currentQuestion = 1,
                        totalQuestions = 4
                    ),
                    onOptionSelected = { selectedIndex ->
                        println("Selected option: $selectedIndex")
                    },
                    onBackPressed = {
                        println("Back pressed from quiz")
                    }
                )
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 20.dp)
        )
    }
}