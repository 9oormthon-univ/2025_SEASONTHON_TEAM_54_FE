package org.ssg_tab.data.remote.datasource

import org.ssg_tab.data.dto.request.OnboardingRequestDto
import org.ssg_tab.data.service.OnboardingService
import javax.inject.Inject

class OnboardingDataSource @Inject constructor(
    private val onboardingService: OnboardingService
) {
    suspend fun submitOnboarding(request: OnboardingRequestDto) {
        onboardingService.submitOnboarding(request)
    }
}