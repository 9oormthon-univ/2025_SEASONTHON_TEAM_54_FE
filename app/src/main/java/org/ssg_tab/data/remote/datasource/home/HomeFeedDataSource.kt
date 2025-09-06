package org.ssg_tab.data.remote.datasource.home

import org.ssg_tab.data.dto.response.base.BaseResponse
import org.ssg_tab.data.dto.response.home.HomeFeedResponseDto


interface HomeFeedDataSource {
    suspend fun getHomeFeed(
        page: Int,
        size: Int,
        categoryId: Int,
    ): BaseResponse<HomeFeedResponseDto>
}