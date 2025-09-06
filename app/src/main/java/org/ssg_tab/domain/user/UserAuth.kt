package org.ssg_tab.domain.user

data class UserAuth(
    val step: String,
    val accessToken: String,
    val refreshToken: String
)

data class SignUpResult(
    val isSuccess: Boolean,
    val message: String,
    val jwtResponse: JwtResponse?
)

data class UserInformationAuth(
    val step : String,
    val accessToken: String,
    val refreshToken: String
)

data class JwtResponse(
    val accessToken: String,
    val refreshToken: String
)