package org.ssg_tab.data.remote.datasourceImpl.home

import org.ssg_tab.data.dto.response.base.BaseResponse
import org.ssg_tab.data.dto.response.home.HomeFeedResponseDto
import org.ssg_tab.data.remote.datasource.home.HomeFeedDataSource
import org.ssg_tab.data.service.home.HomeFeedService
import javax.inject.Inject

class HomeFeedDataSourceImpl @Inject constructor(
    private val homeFeedService: HomeFeedService
) : HomeFeedDataSource {
    override suspend fun getHomeFeed(
        page: Int,
        size: Int,
        categoryId: Int
    ): BaseResponse<HomeFeedResponseDto> {
        return homeFeedService.getHomeFeed(page, size, categoryId)
    }
}