package org.ssg_tab.data.service.home

import org.ssg_tab.data.dto.request.home.HomeLikeRequestDto
import org.ssg_tab.data.dto.response.base.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface HomeLikeService {
    @POST("contents/bookmark")
    suspend fun postHomeLike(
        @Body homeLikeRequestDto: HomeLikeRequestDto
    ): BaseResponse<Unit>
}