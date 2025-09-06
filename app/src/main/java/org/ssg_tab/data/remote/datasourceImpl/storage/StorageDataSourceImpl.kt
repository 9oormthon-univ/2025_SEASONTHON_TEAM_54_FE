package org.ssg_tab.data.remote.datasourceImpl.storage

import org.ssg_tab.core.network.TokenManager
import org.ssg_tab.data.dto.response.base.BaseResponse
import org.ssg_tab.data.dto.response.storage.StorageResponseDto
import org.ssg_tab.data.remote.datasource.storage.StorageDataSource
import org.ssg_tab.data.service.storage.StorageService
import javax.inject.Inject

class StorageDataSourceImpl @Inject constructor(
    private val storageService: StorageService,
    private val tokenManager: TokenManager
) : StorageDataSource {
    override suspend fun getStorage(categoryId: Long): BaseResponse<StorageResponseDto> {
        val accessToken = tokenManager.getAccessToken()
        return storageService.getStorage(
            accessToken = "Bearer $accessToken",
            categoryId = categoryId
        )
    }
}