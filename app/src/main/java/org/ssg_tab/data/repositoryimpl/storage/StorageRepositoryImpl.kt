package org.ssg_tab.data.repositoryimpl.storage

import org.ssg_tab.data.dto.response.storage.StorageResponseDto
import org.ssg_tab.data.dto.response.storage.toEntity
import org.ssg_tab.data.remote.datasourceImpl.storage.StorageDataSourceImpl
import org.ssg_tab.domain.model.entity.storage.StorageEntity
import org.ssg_tab.domain.repository.storage.StorageRepository
import javax.inject.Inject

class StorageRepositoryImpl @Inject constructor(
    private val storageDataSourceImpl: StorageDataSourceImpl,
) : StorageRepository {
    override suspend fun getStorage(categoryId: Long): Result<StorageResponseDto> =
        runCatching {
            val response = storageDataSourceImpl.getStorage(categoryId)
            if (response.isSuccess && response.result != null) {
                response.result.toEntity()
            } else {
                throw Exception(response.message ?: "데이터를 불러오는데 실패했습니다.")
            }
        }
}