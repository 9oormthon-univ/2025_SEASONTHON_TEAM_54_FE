package org.ssg_tab.data.service

import org.ssg_tab.data.dto.response.login.UserInfoResponseDto
import retrofit2.http.GET
import retrofit2.http.Header

interface UserService {
    @GET("/user")
    suspend fun getUserInfo(
        @Header("Authorization") accessToken: String,
    ): UserInfoResponseDto
}