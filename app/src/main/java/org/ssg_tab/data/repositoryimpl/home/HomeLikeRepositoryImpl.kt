package org.ssg_tab.data.repositoryimpl.home

import org.ssg_tab.data.dto.request.home.HomeLikeRequestDto
import org.ssg_tab.data.remote.datasourceImpl.home.HomeLikeDataSourceImpl
import org.ssg_tab.domain.repository.home.HomeLikeRepository
import javax.inject.Inject

class HomeLikeRepositoryImpl @Inject constructor(
    private val homeLikeDataSourceImpl: HomeLikeDataSourceImpl,
) : HomeLikeRepository {
    override suspend fun postHomeLike(homeLikeRequestDto: HomeLikeRequestDto): Result<Unit> =
        runCatching {
            homeLikeDataSourceImpl.postHomeLike(homeLikeRequestDto)
        }
}