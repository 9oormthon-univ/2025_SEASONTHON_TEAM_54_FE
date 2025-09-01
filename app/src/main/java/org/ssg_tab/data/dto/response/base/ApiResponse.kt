package org.ssg_tab.data.dto.response.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    @SerialName("isSuccess")
    val isSuccess: Boolean,
    @SerialName("code")
    val code: String? = null,
    @SerialName("message")
    val message: String,
    @SerialName("result")
    val result: T
)