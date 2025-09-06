package org.ssg_tab.domain.repository.home

import org.ssg_tab.domain.model.entity.home.HomeFeedEntity

interface HomeFeedRepository {
    suspend fun getHomeFeed(
        page: Int,
        size: Int,
        categoryId: Int,
    ): Result<HomeFeedEntity>
}