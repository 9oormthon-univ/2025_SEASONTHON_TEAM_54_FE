package org.ssg_tab.domain.repository.study

import org.ssg_tab.data.dto.response.study.StudyResponseDto

interface StudyRepository {
    suspend fun getLearning(): Result<StudyResponseDto>
}