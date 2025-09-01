package org.ssg_tab.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.ssg_tab.data.dto.response.login.UserInformationDto

@Serializable
data class SignUpRequestDto(
    @SerialName("userInformation")
    val userInformation: UserInformationDto,
    @SerialName("university")
    val university: String,
    @SerialName("grade")
    val grade: String,
    @SerialName("track")
    val track: String,
    @SerialName("major")
    val major: String,
    @SerialName("jobs")
    val jobs: List<String>
)
