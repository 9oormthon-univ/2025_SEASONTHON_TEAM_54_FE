package org.ssg_tab.data.remote.datasource

import org.ssg_tab.data.dto.response.BaseResponse
import org.ssg_tab.data.dto.response.quiz.QuizListResponseDto

interface QuizDataSource {
    suspend fun getQuizList(
        categoryId: Int,
        difficulty: String,
    ): BaseResponse<QuizListResponseDto>
}