package org.ssg_tab.data.service.home

import org.ssg_tab.data.dto.request.home.HomeLikeRequestDto
import org.ssg_tab.data.dto.response.base.BaseResponse
import org.ssg_tab.data.dto.response.home.HomeLikeResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface HomeLikeService {
    @POST("contents/bookmark")
    suspend fun postHomeLike(
        @Header("Authorization") accessToken: String,
        @Body homeLikeRequestDto: HomeLikeRequestDto
    ): HomeLikeResponse
}