package org.ssg_tab.domain.user

data class UserAuth(
    val accessToken: String,
    val refreshToken: String
)

data class SignUpResult(
    val isSuccess: Boolean,
    val message: String,
    val jwtResponse: JwtResponse?
)

data class UserInformationAuth(
    val email: String,
    val nickname: String,
    val profileImageUrl: String
)

data class JwtResponse(
    val accessToken: String,
    val refreshToken: String
)