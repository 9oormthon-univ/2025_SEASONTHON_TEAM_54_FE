package org.ssg_tab.data.remote

import org.ssg_tab.data.service.DummyService
import javax.inject.Inject

class DummyDataSource @Inject constructor (
    private val dummyService: DummyService
) {
    suspend fun getDummyList() = dummyService.getDummyLists()
}