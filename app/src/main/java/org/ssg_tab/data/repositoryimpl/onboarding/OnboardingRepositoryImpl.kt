package org.ssg_tab.data.repositoryimpl.onboarding


import org.ssg_tab.data.mapper.toDto
import org.ssg_tab.data.remote.datasource.OnboardingDataSource
import org.ssg_tab.domain.model.entity.OnboardingEntity
import org.ssg_tab.domain.repository.onboarding.OnboardingRepository
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(
    private val onboardingDataSource: OnboardingDataSource,
) : OnboardingRepository {
    override suspend fun submitOnboardingInfo(
        info: OnboardingEntity
    ): Result<Unit> =
        runCatching {
            val response = onboardingDataSource.submitOnboarding(info.toDto())

            if (response.isSuccess) {
                Unit
            } else {
                throw Exception(response.message ?: "온보딩 정보 저장에 실패했습니다.")
            }
        }
}