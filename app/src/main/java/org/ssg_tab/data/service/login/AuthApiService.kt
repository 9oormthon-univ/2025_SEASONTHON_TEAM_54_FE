package org.ssg_tab.data.service.login

import org.ssg_tab.data.dto.request.SignInRequestDto
import org.ssg_tab.data.dto.request.SignUpRequestDto
import org.ssg_tab.data.dto.response.base.ApiResponse
import org.ssg_tab.data.dto.response.login.SignInResponseDto
import org.ssg_tab.data.dto.response.login.SignUpResponseDto
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApiService {
    @POST("auth/login/kakao")
    suspend fun signIn(
        @Body signInRequestDto: SignInRequestDto
    ): ApiResponse<SignInResponseDto>

    @POST("auth/signup")
    suspend fun signUp(
        @Header("Authorization") accessToken: String,
        @Body signUpRequest: SignUpRequestDto
    ): ApiResponse<SignUpResponseDto>
}