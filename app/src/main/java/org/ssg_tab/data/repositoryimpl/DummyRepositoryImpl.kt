package org.ssg_tab.data.repositoryimpl

import org.ssg_tab.data.mapper.DummyUserMapper
import org.ssg_tab.data.remote.DummyDataSource
import org.ssg_tab.domain.model.entity.DummyUser
import org.ssg_tab.domain.repository.DummyRepository
import javax.inject.Inject


class DummyRepositoryImpl @Inject constructor(
    private val dummyDataSource: DummyDataSource,
    private val mapper: DummyUserMapper
): DummyRepository {
    override suspend fun getDummyList(): Result<List<DummyUser>> = runCatching {
        dummyDataSource.getDummyList().data.map { mapper.mapDtoToEntity(it) }
    }
}