package org.ssg_tab.data.dto.response.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.ssg_tab.data.dto.response.base.BaseResponse

@Serializable
data class UserInfoResultDto(
    @SerialName("nickname")
    val name: String,
    val level: Int,
    val profileImageUrl: String,
)

typealias UserInfoResponseDto = BaseResponse<UserInfoResultDto>