package org.ssg_tab.domain.repository

import org.ssg_tab.domain.model.entity.DummyUser

interface DummyRepository {
    suspend fun getDummyList(): Result<List<DummyUser>>
}