package org.ssg_tab.domain.repository.storage

import org.ssg_tab.data.dto.response.storage.StorageResponseDto

interface StorageRepository {
    suspend fun getStorage(categoryId: Long): Result<StorageResponseDto>
}