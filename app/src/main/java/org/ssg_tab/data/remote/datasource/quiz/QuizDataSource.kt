package org.ssg_tab.data.remote.datasource.quiz

import org.ssg_tab.data.dto.response.base.BaseResponse
import org.ssg_tab.data.dto.response.quiz.QuizListResponseDto

interface QuizDataSource {
    suspend fun getQuizList(
        categoryId: Int,
        difficulty: String,
    ): BaseResponse<QuizListResponseDto>
}