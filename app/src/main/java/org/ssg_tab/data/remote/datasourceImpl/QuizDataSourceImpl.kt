package org.ssg_tab.data.remote.datasourceImpl

import org.ssg_tab.data.dto.response.BaseResponse
import org.ssg_tab.data.dto.response.quiz.QuizListResponseDto
import org.ssg_tab.data.remote.datasource.QuizDataSource
import org.ssg_tab.data.service.QuizService
import javax.inject.Inject

class QuizDataSourceImpl @Inject constructor(
    private val quizApiService: QuizService,
) : QuizDataSource {
    override suspend fun getQuizList(
        categoryId: Int,
        difficulty: String,
    ): BaseResponse<QuizListResponseDto> {
        return quizApiService.getQuizList(categoryId, difficulty)
    }
}