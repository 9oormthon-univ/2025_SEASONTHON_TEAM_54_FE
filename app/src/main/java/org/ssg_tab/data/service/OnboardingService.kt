package org.ssg_tab.data.service

import org.ssg_tab.data.dto.request.OnboardingRequestDto
import org.ssg_tab.data.dto.response.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface OnboardingService {
    @POST("/user/onboarding")
    suspend fun submitOnboarding(
        @Body request: OnboardingRequestDto
    ): BaseResponse<Unit>
}