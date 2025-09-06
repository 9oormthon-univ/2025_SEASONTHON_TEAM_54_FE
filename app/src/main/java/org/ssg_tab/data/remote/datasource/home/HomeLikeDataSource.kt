package org.ssg_tab.data.remote.datasource.home

import org.ssg_tab.data.dto.request.home.HomeLikeRequestDto
import org.ssg_tab.data.dto.response.base.BaseResponse
import org.ssg_tab.data.dto.response.home.HomeLikeResponse

interface HomeLikeDataSource {
    suspend fun postHomeLike(homeLikeRequestDto: HomeLikeRequestDto): HomeLikeResponse
}