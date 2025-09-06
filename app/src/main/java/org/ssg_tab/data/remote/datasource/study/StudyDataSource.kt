package org.ssg_tab.data.remote.datasource.study

import org.ssg_tab.data.dto.response.base.BaseResponse
import org.ssg_tab.data.dto.response.study.StudyResponseDto

interface StudyDataSource {
    suspend fun getLearning(): BaseResponse<StudyResponseDto>
}