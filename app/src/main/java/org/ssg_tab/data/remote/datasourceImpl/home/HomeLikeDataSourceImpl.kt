package org.ssg_tab.data.remote.datasourceImpl.home

import org.ssg_tab.core.network.TokenManager
import org.ssg_tab.data.dto.request.home.HomeLikeRequestDto
import org.ssg_tab.data.dto.response.base.BaseResponse
import org.ssg_tab.data.dto.response.home.HomeLikeResponse
import org.ssg_tab.data.remote.datasource.home.HomeLikeDataSource
import org.ssg_tab.data.service.home.HomeLikeService
import javax.inject.Inject

class HomeLikeDataSourceImpl @Inject constructor(
    private val homeLikeService: HomeLikeService,
    private val tokenManager: TokenManager
) : HomeLikeDataSource {
    override suspend fun postHomeLike(
        homeLikeRequestDto: HomeLikeRequestDto
    ): HomeLikeResponse {
        val accessToken = tokenManager.getAccessToken()
        return homeLikeService.postHomeLike(
            accessToken = "Bearer $accessToken",
            homeLikeRequestDto
        )
    }
}