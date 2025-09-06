package org.ssg_tab.data.remote.datasourceImpl.home

import org.ssg_tab.data.dto.request.home.HomeLikeRequestDto
import org.ssg_tab.data.dto.response.base.BaseResponse
import org.ssg_tab.data.remote.datasource.home.HomeLikeDataSource
import org.ssg_tab.data.service.home.HomeLikeService
import javax.inject.Inject

class HomeLikeDataSourceImpl @Inject constructor(
    private val homeLikeService: HomeLikeService,
) : HomeLikeDataSource {
    override suspend fun postHomeLike(homeLikeRequestDto: HomeLikeRequestDto): BaseResponse<Unit> {
        return homeLikeService.postHomeLike(homeLikeRequestDto)
    }
}