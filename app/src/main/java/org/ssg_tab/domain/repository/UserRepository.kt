package org.ssg_tab.domain.repository

import org.ssg_tab.domain.model.entity.UserInfo


interface UserRepository {
    suspend fun getUserInfo(): Result<UserInfo>
}