package org.ssg_tab.data.remote.datasource

import org.ssg_tab.data.service.UserService
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val userService: UserService
) {
    suspend fun getUserInfo() = userService.getUserInfo()
}