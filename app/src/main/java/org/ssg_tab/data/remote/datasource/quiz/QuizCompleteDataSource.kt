package org.ssg_tab.data.remote.datasource.quiz

import org.ssg_tab.data.dto.request.quiz.QuizCompleteRequestDto
import org.ssg_tab.data.dto.response.base.BaseResponse

interface QuizCompleteDataSource {
    suspend fun completeQuiz(quizCompleteRequestDto: QuizCompleteRequestDto): BaseResponse<Unit>
}