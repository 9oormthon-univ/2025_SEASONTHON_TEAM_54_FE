package org.ssg_tab.domain.repository.quiz

import org.ssg_tab.data.dto.request.quiz.QuizCompleteRequestDto

interface QuizCompleteRepository {
    suspend fun completeQuiz(quizCompleteRequestDto: QuizCompleteRequestDto): Result<Unit>
}