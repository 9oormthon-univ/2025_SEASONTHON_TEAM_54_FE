package org.ssg_tab.data.remote.datasourceImpl.quiz

import org.ssg_tab.core.network.TokenManager
import org.ssg_tab.data.dto.request.quiz.QuizCompleteRequestDto
import org.ssg_tab.data.dto.response.base.BaseResponse
import org.ssg_tab.data.remote.datasource.quiz.QuizCompleteDataSource
import org.ssg_tab.data.service.quiz.QuizCompleteService
import javax.inject.Inject

class QuizCompleteDataSourceImpl @Inject constructor(
    private val quizCompleteService: QuizCompleteService,
    private val tokenManager: TokenManager
) : QuizCompleteDataSource {
    override suspend fun completeQuiz(quizCompleteRequestDto: QuizCompleteRequestDto): BaseResponse<Unit> {
        val accessToken = tokenManager.getAccessToken()
        return quizCompleteService.completeQuiz(
            accessToken = "Bearer $accessToken",
            quizCompleteRequestDto = quizCompleteRequestDto
        )
    }
}