package org.ssg_tab.data.remote.datasourceImpl

import org.ssg_tab.data.dto.request.SignInRequestDto
import org.ssg_tab.data.dto.request.SignUpRequestDto
import org.ssg_tab.data.dto.response.base.ApiResponse
import org.ssg_tab.data.dto.response.login.SignInResponseDto
import org.ssg_tab.data.dto.response.login.SignUpResponseDto
import org.ssg_tab.data.remote.datasource.AuthRemoteDataSource
import org.ssg_tab.data.service.login.AuthApiService
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authApiService: AuthApiService
) : AuthRemoteDataSource {

    override suspend fun signIn(accessToken: String, socialType: String): ApiResponse<SignInResponseDto> {
        return authApiService.signIn(signInRequestDto = SignInRequestDto(kakaoAccessToken = "$accessToken"))
    }

    override suspend fun signUp(accessToken: String, signUpRequest: SignUpRequestDto): ApiResponse<SignUpResponseDto> {
        return authApiService.signUp(accessToken, signUpRequest)
    }
}