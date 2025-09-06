package org.ssg_tab.data.service.storage

import org.ssg_tab.data.dto.response.base.BaseResponse
import org.ssg_tab.data.dto.response.storage.StorageResponseDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface StorageService {
    @GET("contents/bookmark")
    suspend fun getStorage(
        @Header("Authorization") accessToken: String,
        @Query ("categoryId") categoryId: Long,
    ):BaseResponse<StorageResponseDto>
}

