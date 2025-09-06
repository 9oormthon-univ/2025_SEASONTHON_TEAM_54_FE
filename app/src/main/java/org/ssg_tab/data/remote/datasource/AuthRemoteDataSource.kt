package org.ssg_tab.data.remote.datasource

import org.ssg_tab.data.dto.request.SignInRequestDto
import org.ssg_tab.data.dto.request.SignUpRequestDto
import org.ssg_tab.data.dto.response.base.ApiResponse
import org.ssg_tab.data.dto.response.login.SignInResponseDto
import org.ssg_tab.data.dto.response.login.SignUpResponseDto

interface AuthRemoteDataSource {
    suspend fun signIn(signInRequestDto: SignInRequestDto): ApiResponse<SignInResponseDto>
    suspend fun signUp(accessToken: String, signUpRequest: SignUpRequestDto): ApiResponse<SignUpResponseDto>
}