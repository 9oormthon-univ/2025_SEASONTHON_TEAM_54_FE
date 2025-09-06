package org.ssg_tab.domain.repository.home

import org.ssg_tab.data.dto.request.home.HomeLikeRequestDto

interface HomeLikeRepository {
    suspend fun postHomeLike(homeLikeRequestDto: HomeLikeRequestDto): Result<Unit>
}