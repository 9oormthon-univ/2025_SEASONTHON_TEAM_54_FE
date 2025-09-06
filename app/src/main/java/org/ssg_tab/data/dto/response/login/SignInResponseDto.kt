package org.ssg_tab.data.dto.response.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInResponseDto(
    @SerialName("step")
    val step: String,
    @SerialName("accessToken")
    val accessToken: String,
    @SerialName("refreshToken")
    val refreshToken: String,
)

@Serializable
data class UserInformationDto(
    @SerialName("step")
    val step: String,
    @SerialName("accessToken")
    val accessToken: String,
    @SerialName("refreshToken")
    val refreshToken: String,
)


fun SignInResponseDto.toEntity(): SignInResponseDto = SignInResponseDto(
    step = this.step,
    accessToken = this.accessToken,
    refreshToken = this.refreshToken
)