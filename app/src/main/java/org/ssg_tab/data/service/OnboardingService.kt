package org.ssg_tab.data.service

import org.ssg_tab.data.dto.request.OnboardingRequestDto
import org.ssg_tab.data.dto.response.base.BaseResponse
import org.ssg_tab.data.dto.response.login.OnboardingResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface OnboardingService {
    @POST("/user/onboarding")
    suspend fun submitOnboarding(
        @Header ("Authorization") accessToken: String,
        @Body request: OnboardingRequestDto
    ): OnboardingResponse
}