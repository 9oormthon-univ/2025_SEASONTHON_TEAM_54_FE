// UserInfo.kt
package org.ssg_tab.domain.model.entity

import org.ssg_tab.data.dto.response.login.UserInfoResultDto

data class UserInfo(
    val name: String,
    val level: Int,
    val profileImageUrl: String
)
