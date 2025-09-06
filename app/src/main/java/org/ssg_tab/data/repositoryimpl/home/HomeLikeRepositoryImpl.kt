package org.ssg_tab.data.repositoryimpl.home

import org.ssg_tab.data.dto.request.home.HomeLikeRequestDto
import org.ssg_tab.data.dto.response.home.toEntity
import org.ssg_tab.data.remote.datasourceImpl.home.HomeLikeDataSourceImpl
import org.ssg_tab.domain.repository.home.HomeLikeRepository
import javax.inject.Inject

class HomeLikeRepositoryImpl @Inject constructor(
    private val homeLikeDataSourceImpl: HomeLikeDataSourceImpl,
) : HomeLikeRepository {
    override suspend fun postHomeLike(homeLikeRequestDto: HomeLikeRequestDto): Result<Unit> =
        runCatching {
            val response = homeLikeDataSourceImpl.postHomeLike(homeLikeRequestDto)
            if (response.isSuccess) {
                Unit // Unit 반환 (response.result는 이미 Unit이므로 그대로 사용 가능)
            } else {
                throw Exception(response.message ?: "좋아요 처리에 실패했습니다.")
            }
        }
}