package org.ssg_tab.data.dto.request.home

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeLikeRequestDto (
    @SerialName ("conteentsId")
    val contentsId: Int,
)