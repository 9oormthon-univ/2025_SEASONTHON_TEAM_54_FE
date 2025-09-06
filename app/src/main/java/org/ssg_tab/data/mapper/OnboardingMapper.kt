package org.ssg_tab.data.mapper

import org.ssg_tab.data.dto.request.OnboardingRequestDto
import org.ssg_tab.domain.model.entity.OnboardingEntity

fun OnboardingEntity.toDto(): OnboardingRequestDto {
    return OnboardingRequestDto(
        nickname = null,
        profileImageUrl = null,
        ageBand = this.ageBand,
        region = this.region,
        job = this.job,
        categoryIds = this.categoryIds
    )
}