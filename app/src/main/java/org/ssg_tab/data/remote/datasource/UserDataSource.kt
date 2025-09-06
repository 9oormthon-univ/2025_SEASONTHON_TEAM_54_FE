package org.ssg_tab.data.remote.datasource

import org.ssg_tab.core.network.TokenManager
import org.ssg_tab.data.dto.response.login.UserInfoResponseDto
import org.ssg_tab.data.service.UserService
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val userService: UserService,
    private val tokenManager: TokenManager
) {
    suspend fun getUserInfo(): UserInfoResponseDto {
        val accessToken = tokenManager.getAccessToken()
        return userService.getUserInfo("Bearer $accessToken")
    }
}