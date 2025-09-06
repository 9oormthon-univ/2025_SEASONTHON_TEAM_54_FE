package org.ssg_tab.data.service.home

import org.ssg_tab.data.dto.response.base.BaseResponse
import org.ssg_tab.data.dto.response.home.HomeFeedResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeFeedService {
    @GET("/contents")
    suspend fun getHomeFeed(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("categoryId") categoryId: Int,
    ): BaseResponse<HomeFeedResponseDto>
}