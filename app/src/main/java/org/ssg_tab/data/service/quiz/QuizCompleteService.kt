package org.ssg_tab.data.service.quiz

import org.ssg_tab.data.dto.request.quiz.QuizCompleteRequestDto
import org.ssg_tab.data.dto.response.base.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface QuizCompleteService {
    @POST ("/quiz")
    suspend fun completeQuiz(
        @Body quizCompleteRequestDto: QuizCompleteRequestDto
    ):BaseResponse<Unit>
}