package org.ssg_tab.data.repositoryimpl.login

import org.ssg_tab.data.dto.request.SignUpRequestDto
import org.ssg_tab.data.dto.response.login.UserInformationDto
import org.ssg_tab.data.remote.datasource.AuthRemoteDataSource
import org.ssg_tab.data.util.HttpResponseHandler.handleApiResponse
import org.ssg_tab.data.util.safeApiCall
import org.ssg_tab.domain.repository.login.AuthRepository
import org.ssg_tab.domain.user.JwtResponse
import org.ssg_tab.domain.user.SignUpResult
import org.ssg_tab.domain.user.UserAuth
import org.ssg_tab.domain.user.UserInformationAuth
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
) : AuthRepository {
    override suspend fun signIn(accessToken: String, socialType: String): Result<UserAuth> =
        safeApiCall {
            val response = authRemoteDataSource.signIn(accessToken = accessToken, socialType = socialType)
                .handleApiResponse()
                .getOrThrow()

            UserAuth(
                accessToken = response.accessToken,
                refreshToken = response.refreshToken
            )
        }

    override suspend fun signUp(
        preSignupToken: String,
        userInformation: UserInformationAuth?,
        university: String,
        grade: String,
        track: String,
        major: String,
        jobs: List<String>,
    ): Result<SignUpResult> = safeApiCall {
        val userInfoDto = UserInformationDto(
            email = userInformation?.email ?: "",
            nickname = userInformation?.nickname ?: "",
            profileImageUrl = userInformation?.profileImageUrl ?: ""
        )
        val signUpRequestDto = SignUpRequestDto(
            userInformation = userInfoDto,
            university = university,
            grade = grade,
            track = track,
            major = major,
            jobs = jobs
        )

        val response = authRemoteDataSource.signUp(
            accessToken = "Bearer $preSignupToken",
            signUpRequest = signUpRequestDto
        ).handleApiResponse()
            .getOrThrow()

        SignUpResult(
            isSuccess = response.isSuccess ?: false,
            message = response.message ?: "",
            jwtResponse = JwtResponse(
                accessToken = response.jwtResponse?.accessToken ?: "",
                refreshToken = response.jwtResponse?.refreshToken ?: ""
            )
        )
    }
}