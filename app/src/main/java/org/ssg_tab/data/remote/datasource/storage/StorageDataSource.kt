package org.ssg_tab.data.remote.datasource.storage

import org.ssg_tab.data.dto.response.base.BaseResponse
import org.ssg_tab.data.dto.response.storage.StorageResponseDto

interface StorageDataSource {
    suspend fun getStorage(categoryId: Long): BaseResponse<StorageResponseDto>
}