package org.ssg_tab.data.remote.datasourceImpl.home

import org.ssg_tab.core.network.TokenManager
import org.ssg_tab.data.dto.response.base.BaseResponse
import org.ssg_tab.data.dto.response.home.HomeFeedResponseDto
import org.ssg_tab.data.remote.datasource.home.HomeFeedDataSource
import org.ssg_tab.data.service.home.HomeFeedService
import javax.inject.Inject

class HomeFeedDataSourceImpl @Inject constructor(
    private val homeFeedService: HomeFeedService,
    private val tokenManager: TokenManager
) : HomeFeedDataSource {
    override suspend fun getHomeFeed(
        page: Int,
        size: Int,
        categoryId: Int
    ): BaseResponse<HomeFeedResponseDto> {
        val accessToken = tokenManager.getAccessToken()
        return homeFeedService.getHomeFeed(
            accessToken = "Bearer $accessToken",
            page = page,
            size = size,
            categoryId = categoryId
        )
    }
}