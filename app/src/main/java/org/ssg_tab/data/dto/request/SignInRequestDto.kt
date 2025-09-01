package org.ssg_tab.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInRequestDto(
    @SerialName("kakaoAccessToken")
    val kakaoAccessToken: String
)