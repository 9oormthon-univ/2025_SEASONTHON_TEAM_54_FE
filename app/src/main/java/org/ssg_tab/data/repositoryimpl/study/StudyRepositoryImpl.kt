package org.ssg_tab.data.repositoryimpl.study

import org.ssg_tab.data.dto.response.study.StudyResponseDto
import org.ssg_tab.data.dto.response.study.toEntity
import org.ssg_tab.data.remote.datasourceImpl.study.StudyDataSourceImpl
import org.ssg_tab.domain.repository.study.StudyRepository
import javax.inject.Inject

class StudyRepositoryImpl @Inject constructor(
    private val studyDataSourceImpl: StudyDataSourceImpl,
) : StudyRepository {
    override suspend fun getLearning(): Result<StudyResponseDto> =
        runCatching {
            val response = studyDataSourceImpl.getLearning()
            if (response.isSuccess && response.result != null) {
                response.result.toEntity()
            } else {
                throw Exception(response.message ?: "학습 데이터를 불러오는데 실패했습니다.")
            }
        }
}