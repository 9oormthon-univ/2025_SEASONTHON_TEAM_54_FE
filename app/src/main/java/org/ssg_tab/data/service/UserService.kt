package org.ssg_tab.data.service

import org.ssg_tab.data.dto.response.login.UserInfoResponseDto
import retrofit2.http.GET

interface UserService {
    @GET("/users")
    suspend fun getUserInfo(): UserInfoResponseDto
}