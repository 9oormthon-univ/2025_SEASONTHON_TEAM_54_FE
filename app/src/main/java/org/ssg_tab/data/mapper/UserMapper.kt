package org.ssg_tab.data.mapper

import org.ssg_tab.data.dto.response.login.UserInfoResultDto
import org.ssg_tab.domain.model.entity.UserInfo

fun UserInfoResultDto.toEntity(): UserInfo {
    return UserInfo(
        name = this.name,
        level = this.level,
        profileImageUrl = this.profileImageUrl,
    )
}