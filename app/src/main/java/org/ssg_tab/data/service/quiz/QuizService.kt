package org.ssg_tab.data.service.quiz

import org.ssg_tab.data.dto.response.base.BaseResponse
import org.ssg_tab.data.dto.response.quiz.QuizListResponseDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface QuizService {
    @GET("/quiz")
    suspend fun getQuizList(
        @Header("Authorization") accessToken: String,
        @Query("categoryId") categoryId: Int,
        @Query("difficulty") difficulty: String,
    ): BaseResponse<QuizListResponseDto>
}