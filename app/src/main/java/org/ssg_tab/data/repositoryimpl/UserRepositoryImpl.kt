package org.ssg_tab.data.repositoryimpl

import org.ssg_tab.data.mapper.toEntity
import org.ssg_tab.data.remote.datasource.UserDataSource
import org.ssg_tab.domain.model.entity.UserInfo
import org.ssg_tab.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {
    override suspend fun getUserInfo(): Result<UserInfo> {
        return runCatching {
            val response = userDataSource.getUserInfo()
            if (response.isSuccess && response.result != null) {
                response.result.toEntity()
            } else {
                throw Exception(response.message ?: "사용자 정보를 불러오는데 실패했습니다.")
            }
        }
    }
}