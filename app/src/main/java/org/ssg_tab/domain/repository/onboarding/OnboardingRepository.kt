package org.ssg_tab.domain.repository.onboarding

import org.ssg_tab.domain.model.entity.OnboardingEntity

interface OnboardingRepository {
    suspend fun submitOnboardingInfo(info: OnboardingEntity): Result<Unit>
}