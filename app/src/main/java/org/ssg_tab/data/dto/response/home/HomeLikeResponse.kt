package org.ssg_tab.data.dto.response.home

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeLikeResponse(
    @SerialName("isSuccess")
    val isSuccess: Boolean,
    @SerialName("code")
    val code: String,
    @SerialName("message")
    val message: String
)