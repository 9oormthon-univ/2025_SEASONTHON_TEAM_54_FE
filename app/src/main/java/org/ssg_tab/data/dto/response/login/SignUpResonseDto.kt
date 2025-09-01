package org.ssg_tab.data.dto.response.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponseDto(
    @SerialName("isSuccess")
    val isSuccess: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: SignUpDataDto? = null,
    @SerialName("jwtResponse")
    val jwtResponse: TokenResponseDto,
)

@Serializable
data class SignUpDataDto(
    @SerialName("userId")
    val userId: Long,
    @SerialName("nickName")
    val nickName: String,
    @SerialName("university")
    val university: String,
    @SerialName("trackType")
    val trackType: String,
    @SerialName("major")
    val major: String,
    @SerialName("jobs")
    val jobs: List<String>,
)
