package org.ssg_tab.presentation.ui.guide.state

import androidx.compose.runtime.Immutable
import org.ssg_tab.domain.model.entity.quiz.QuizEntity

@Immutable
data class QuizContract(
    val quizList: List<QuizEntity> = emptyList(),
    val currentQuizIndex: Int = 0,
    val selectedAnswer: Int = -1,
    val isLoading: Boolean = false,
    val error: String? = null,
) {
    val currentQuiz: QuizEntity?
        get() = quizList.getOrNull(currentQuizIndex)

    val totalQuestions: Int
        get() = quizList.size
}