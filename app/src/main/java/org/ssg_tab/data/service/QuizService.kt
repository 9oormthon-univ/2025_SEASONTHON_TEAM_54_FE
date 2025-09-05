package org.ssg_tab.data.service

import org.ssg_tab.data.dto.response.BaseResponse
import org.ssg_tab.data.dto.response.quiz.QuizListResponseDto
import retrofit2.http.GET
import retrofit2.http.Query


interface QuizService {
    @GET("/quiz")
    suspend fun getQuizList(
        @Query("categoryId") categoryId: Int,
        @Query("difficulty") difficulty: String,
    ): BaseResponse<QuizListResponseDto>
}