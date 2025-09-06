package org.ssg_tab.data.remote.datasource

import org.ssg_tab.core.network.TokenManager
import org.ssg_tab.data.dto.request.OnboardingRequestDto
import org.ssg_tab.data.dto.response.base.BaseResponse
import org.ssg_tab.data.dto.response.login.OnboardingResponse
import org.ssg_tab.data.service.OnboardingService
import javax.inject.Inject

class OnboardingDataSource @Inject constructor(
    private val onboardingService: OnboardingService,
    private val tokenManager: TokenManager
) {
    suspend fun submitOnboarding(request: OnboardingRequestDto): OnboardingResponse {
        val accessToken = tokenManager.getAccessToken()
        return onboardingService.submitOnboarding(
            accessToken = "Bearer $accessToken",
            request = request
        )
    }
}