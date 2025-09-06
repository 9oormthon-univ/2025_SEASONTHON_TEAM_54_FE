package org.ssg_tab.data.service.study

import org.ssg_tab.data.dto.response.base.BaseResponse
import org.ssg_tab.data.dto.response.study.StudyResponseDto
import retrofit2.http.GET
import retrofit2.http.Header

interface StudyService {
    @GET("user/learning")
    suspend fun getLearning(
        @Header("Authorization") accessToken: String,
    ):BaseResponse<StudyResponseDto>
}