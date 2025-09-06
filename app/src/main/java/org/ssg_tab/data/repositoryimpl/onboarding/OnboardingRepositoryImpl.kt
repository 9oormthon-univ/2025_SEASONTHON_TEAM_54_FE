package org.ssg_tab.data.repositoryimpl.onboarding


import org.ssg_tab.data.mapper.toDto
import org.ssg_tab.data.remote.datasource.OnboardingDataSource // DataSource import
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
            onboardingDataSource.submitOnboarding(info.toDto())
        }
}