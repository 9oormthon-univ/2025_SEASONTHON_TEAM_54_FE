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
            homeFeedDataSourceImpl.getHomeFeed(page, size, categoryId).result.toEntity()
        }
}

