package org.ssg_tab.domain.repository.login

import org.ssg_tab.data.dto.request.SignInRequestDto
import org.ssg_tab.domain.user.SignUpResult
import org.ssg_tab.domain.user.UserAuth
import org.ssg_tab.domain.user.UserInformationAuth

interface AuthRepository {
    suspend fun signIn(signInRequestDto: SignInRequestDto): Result<UserAuth>
    suspend fun signUp(
        preSignupToken: String,
        userInformation: UserInformationAuth?,
        university: String,
        grade: String,
        track: String,
        major: String,
        jobs: List<String>
    ): Result<SignUpResult>
}