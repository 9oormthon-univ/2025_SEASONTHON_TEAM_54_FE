package org.ssg_tab.data.remote.datasourceImpl.study

import org.ssg_tab.core.network.TokenManager
import org.ssg_tab.data.dto.response.base.BaseResponse
import org.ssg_tab.data.dto.response.study.StudyResponseDto
import org.ssg_tab.data.remote.datasource.study.StudyDataSource
import org.ssg_tab.data.service.study.StudyService
import javax.inject.Inject

class StudyDataSourceImpl @Inject constructor(
    private val studyService: StudyService,
    private val tokenManager: TokenManager
) : StudyDataSource {
    override suspend fun getLearning(): BaseResponse<StudyResponseDto> {
        val accessToken = tokenManager.getAccessToken()
        return studyService.getLearning("Bearer $accessToken")
    }
}