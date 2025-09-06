package org.ssg_tab.presentation.ui.guide

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import org.ssg_tab.R
import org.ssg_tab.core.designsystem.theme.SsgTabTheme
import org.ssg_tab.domain.model.entity.quiz.QuizEntity
import org.ssg_tab.presentation.ui.guide.model.QuizViewModel

data class QuizData(
    val question: String,
    val options: List<String>,
    val currentQuestion: Int,
    val totalQuestions: Int,
)

@Preview(showBackground = true)
@Composable
private fun PreviewQuizScreen() {
    SsgTabTheme {
        QuizScreen(
            quizData = QuizData(
                question = "창업통증과 남일인정에이란 무엇일까요?",
                options = listOf(
                    "창업통증과 넘는 총 금액",
                    "창업 참수 계시의 상대 바당되는 금액",
                    "기술이 도유한 창업통증자가 모두 받는 금액",
                    "당월 시 네어 하는 계약금"
                ),
                currentQuestion = 1,
                totalQuestions = 4
            ),
            onOptionSelected = { },
            onBackPressed = { }
        )
    }
}

@Composable
fun QuizScreenWithViewModel(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: QuizViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    when {
        state.isLoading -> {
            LoadingScreen()
        }

        state.error != null -> {
            ErrorScreen(
                error = state.error!!,
                onRetry = { viewModel.loadQuizzes() },
                onBackPressed = onBackPressed
            )
        }

        state.quizList.isEmpty() -> {
            EmptyScreen(onBackPressed = onBackPressed)
        }

        else -> {
            val currentQuiz = state.currentQuiz
            if (currentQuiz != null) {
                QuizContent(
                    quiz = currentQuiz,
                    currentQuizIndex = state.currentQuizIndex,
                    totalQuestions = state.totalQuestions,
                    selectedAnswer = state.selectedAnswer,
                    onOptionSelected = { viewModel.selectAnswer(it) },
                    onPreviousQuestion = { viewModel.previousQuestion() },
                    onNextQuestion = { viewModel.nextQuestion() },
                    onBackPressed = onBackPressed,
                    isFirstQuestion = viewModel.isFirstQuestion(),
                    isLastQuestion = viewModel.isLastQuestion(),
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
private fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SsgTabTheme.colors.White),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = Color(0xFF42A5F5)
        )
    }
}

@Composable
private fun ErrorScreen(
    error: String,
    onRetry: () -> Unit,
    onBackPressed: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SsgTabTheme.colors.White)
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "오류가 발생했습니다",
            style = SsgTabTheme.typography.Large_Sb,
            color = SsgTabTheme.colors.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = error,
            style = SsgTabTheme.typography.Regular_R,
            color = SsgTabTheme.colors.MidGray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row {
            Box(
                modifier = Modifier
                    .background(
                        color = Color(0xFF42A5F5),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable { onRetry() }
                    .padding(16.dp)
            ) {
                Text(
                    text = "다시 시도",
                    style = SsgTabTheme.typography.Regular_Sb,
                    color = SsgTabTheme.colors.White
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Box(
                modifier = Modifier
                    .background(
                        color = SsgTabTheme.colors.LightGray,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable { onBackPressed() }
                    .padding(16.dp)
            ) {
                Text(
                    text = "뒤로가기",
                    style = SsgTabTheme.typography.Regular_Sb,
                    color = SsgTabTheme.colors.Black
                )
            }
        }
    }
}

@Composable
private fun EmptyScreen(onBackPressed: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SsgTabTheme.colors.White)
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "퀴즈가 없습니다",
            style = SsgTabTheme.typography.Large_Sb,
            color = SsgTabTheme.colors.Black
        )

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .background(
                    color = SsgTabTheme.colors.LightGray,
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable { onBackPressed() }
                .padding(16.dp)
        ) {
            Text(
                text = "뒤로가기",
                style = SsgTabTheme.typography.Regular_Sb,
                color = SsgTabTheme.colors.Black
            )
        }
    }
}

@Composable
private fun QuizContent(
    quiz: QuizEntity,
    currentQuizIndex: Int,
    totalQuestions: Int,
    selectedAnswer: Int,
    onOptionSelected: (Int) -> Unit,
    onPreviousQuestion: () -> Unit,
    onNextQuestion: () -> Unit,
    onBackPressed: () -> Unit,
    isFirstQuestion: Boolean,
    isLastQuestion: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SsgTabTheme.colors.White)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_core_back),
                contentDescription = "뒤로가기",
                tint = SsgTabTheme.colors.LightGray,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onBackPressed() }
            )

            Text(
                text = "창업 기초",
                style = SsgTabTheme.typography.Regular_Sb,
                color = SsgTabTheme.colors.Black
            )

            Spacer(modifier = Modifier.size(24.dp))
        }

        LinearProgressIndicator(
            progress = { (currentQuizIndex + 1).toFloat() / totalQuestions },
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .clip(RoundedCornerShape(2.dp)),
            color = Color(0xFF42A5F5),
            trackColor = SsgTabTheme.colors.LightGray,
            gapSize = 0.dp,
            drawStopIndicator = {}

        )

        Spacer(modifier = Modifier.height(40.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(8.dp)
                .background(
                    color = SsgTabTheme.colors.White,
                    shape = RoundedCornerShape(36.dp)
                )
                .clip(RoundedCornerShape(36.dp))
                .border(
                    width = 1.dp,
                    color = SsgTabTheme.colors.LightGray,
                    shape = RoundedCornerShape(36.dp),
                )
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 20.dp, start = 12.dp, end = 20.dp),
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = "Q.",
                    style = SsgTabTheme.typography.Large_Sb,
                    color = Color(0xFF42A5F5),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = quiz.question,
                    style = SsgTabTheme.typography.Large_Sb,
                    color = SsgTabTheme.colors.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 28.sp
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 80.dp, start = 20.dp, end = 20.dp)
            ) {
                quiz.options.forEachIndexed { index, option ->
                    QuizOptionItem(
                        text = option,
                        isSelected = selectedAnswer == index,
                        onClick = {
                            onOptionSelected(index)
                        }
                    )

                    if (index < quiz.options.size - 1) {
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier
                .background(
                    color = SsgTabTheme.colors.White,
                    shape = RoundedCornerShape(20.dp)
                )
                .clip(RoundedCornerShape(20.dp))
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_core_back),
                contentDescription = "이전",
                tint = if (isFirstQuestion) SsgTabTheme.colors.LightGray else Color(0xFF42A5F5),
                modifier = Modifier
                    .size(16.dp)
                    .clickable(enabled = !isFirstQuestion) { onPreviousQuestion() }
            )

            Text(
                text = "${currentQuizIndex + 1} / $totalQuestions",
                style = SsgTabTheme.typography.Regular_R,
                color = SsgTabTheme.colors.LightGray
            )

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_study_arrow_right),
                contentDescription = "다음",
                tint = if (isLastQuestion) SsgTabTheme.colors.LightGray else Color(0xFF42A5F5),
                modifier = Modifier
                    .size(16.dp)
                    .clickable(enabled = !isLastQuestion) { onNextQuestion() }
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun QuizScreen(
    quizData: QuizData,
    onOptionSelected: (Int) -> Unit,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedOption by remember { mutableIntStateOf(-1) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SsgTabTheme.colors.White)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_core_back),
                contentDescription = "뒤로가기",
                tint = SsgTabTheme.colors.LightGray,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onBackPressed() }
            )

            Text(
                text = "창업 기초",
                style = SsgTabTheme.typography.Regular_Sb,
                color = SsgTabTheme.colors.Black
            )

            Spacer(modifier = Modifier.size(24.dp))
        }

        Spacer(modifier = Modifier.height(20.dp))

        LinearProgressIndicator(
            progress = { quizData.currentQuestion.toFloat() / quizData.totalQuestions },
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .clip(RoundedCornerShape(2.dp)),
            color = Color(0xFF42A5F5),
            trackColor = SsgTabTheme.colors.LightGray,
        )

        Spacer(modifier = Modifier.height(40.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(8.dp)
                .background(
                    color = SsgTabTheme.colors.White,
                    shape = RoundedCornerShape(36.dp)
                )
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(36.dp)
                )
                .clip(RoundedCornerShape(36.dp))
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 20.dp, start = 12.dp, end = 20.dp),
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = "Q.",
                    style = SsgTabTheme.typography.Large_Sb,
                    color = Color(0xFF42A5F5),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = quizData.question,
                    style = SsgTabTheme.typography.Large_Sb,
                    color = SsgTabTheme.colors.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 28.sp
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 80.dp, start = 20.dp, end = 20.dp)
            ) {
                quizData.options.forEachIndexed { index, option ->
                    QuizOptionItem(
                        text = option,
                        isSelected = selectedOption == index,
                        onClick = {
                            selectedOption = index
                            onOptionSelected(index)
                        }
                    )

                    if (index < quizData.options.size - 1) {
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }

            }


        }

        Spacer(modifier = Modifier.height(64.dp))

        Row(
            modifier = Modifier
                .background(
                    color = SsgTabTheme.colors.White,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(horizontal = 144.dp, vertical = 12.dp)
                .clip(RoundedCornerShape(20.dp)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_core_back),
                contentDescription = "이전",
                tint = SsgTabTheme.colors.LightGray,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .size(16.dp)
            )


            Text(
                modifier = Modifier
                    .padding(vertical = 12.dp),
                text = "${quizData.currentQuestion} / ${quizData.totalQuestions}",
                style = SsgTabTheme.typography.Regular_R,
                color = SsgTabTheme.colors.LightGray
            )


            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_study_arrow_right),
                contentDescription = "다음",
                tint = SsgTabTheme.colors.LightGray,
                modifier = Modifier
                    .size(16.dp)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
private fun QuizOptionItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = if (isSelected) {
                    Color(0xFFE3F2FD)
                } else {
                    SsgTabTheme.colors.WhiteGray
                },
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Text(
            text = text,
            style = SsgTabTheme.typography.Regular_R,
            color = if (isSelected) {
                Color(0xFF42A5F5)
            } else {
                SsgTabTheme.colors.MidGray
            },
            lineHeight = 20.sp
        )
    }
}