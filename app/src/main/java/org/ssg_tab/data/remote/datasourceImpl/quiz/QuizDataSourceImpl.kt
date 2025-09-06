package org.ssg_tab.data.remote.datasourceImpl.quiz

import org.ssg_tab.core.network.TokenManager
import org.ssg_tab.data.dto.response.base.BaseResponse
import org.ssg_tab.data.dto.response.quiz.QuizListResponseDto
import org.ssg_tab.data.remote.datasource.quiz.QuizDataSource
import org.ssg_tab.data.service.quiz.QuizService
import javax.inject.Inject

class QuizDataSourceImpl @Inject constructor(
    private val quizApiService: QuizService,
    private val tokenManager: TokenManager
) : QuizDataSource {
    override suspend fun getQuizList(
        categoryId: Int,
        difficulty: String,
    ): BaseResponse<QuizListResponseDto> {
        val accessToken = tokenManager.getAccessToken()
        return quizApiService.getQuizList(
            accessToken = "Bearer $accessToken",
            categoryId,
            difficulty)
    }
}