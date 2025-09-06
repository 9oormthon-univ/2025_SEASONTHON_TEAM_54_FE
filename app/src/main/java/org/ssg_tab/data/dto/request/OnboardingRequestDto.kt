package org.ssg_tab.data.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class OnboardingRequestDto(
    val nickname: String?,
    val profileImageUrl: String?,
    val ageBand: String,
    val region: String,
    val job: String,
    val categoryIds: List<Long>
)