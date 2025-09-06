package org.ssg_tab.data.repositoryimpl.home

import org.ssg_tab.data.dto.response.home.toEntity
import org.ssg_tab.data.remote.datasourceImpl.home.HomeFeedDataSourceImpl
import org.ssg_tab.domain.model.entity.home.HomeFeedEntity
import org.ssg_tab.domain.repository.home.HomeFeedRepository
import javax.inject.Inject

class HomeFeedRepositoryImpl @Inject constructor(
    private val homeFeedDataSourceImpl: HomeFeedDataSourceImpl,
) : HomeFeedRepository {
    override suspend fun getHomeFeed(
        page: Int,
        size: Int,
        categoryId: Int,
    ): Result<HomeFeedEntity> =
        runCatching {
            val response = homeFeedDataSourceImpl.getHomeFeed(page, size, categoryId)

            // BaseResponse의 isSuccess 확인
            if (response.isSuccess) {
                response.result.toEntity()
            } else {
                throw Exception(response.message ?: "홈 피드를 불러오는데 실패했습니다.")
            }
        }
}

